package pro.ms.billetera.application.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pro.ms.billetera.application.dto.command_usecase.TransaccionCobroCommand;
import pro.ms.billetera.application.event.CobroRealizadoEvent;
import pro.ms.billetera.application.exception.CuentaNoEncontradaException;
import pro.ms.billetera.application.exception.MontoInvalidoException;
import pro.ms.billetera.application.port.in.transaccion.CobrarTransaccionExternoUseCase;
import pro.ms.billetera.application.port.out.event.EventPublisher;
import pro.ms.billetera.application.port.out.repository.CuentaRepository;
import pro.ms.billetera.application.port.out.repository.MonedaRepository;
import pro.ms.billetera.application.port.out.repository.TransaccionCobroRepository;
import pro.ms.billetera.domain.model.Cuenta;
import pro.ms.billetera.domain.model.Moneda;
import pro.ms.billetera.domain.model.Transaccion;
import pro.ms.billetera.domain.model.detalle_transaccion.DetalleCobro;
import pro.ms.billetera.domain.model.detalle_transaccion.DetalleTransaccion;

@Slf4j
@RequiredArgsConstructor
public class CobrarTransaccionExternoUseCaseImpl implements CobrarTransaccionExternoUseCase {


    private final CuentaRepository cuentaRepository;
    private final MonedaRepository monedaRepository;
    private final TransaccionCobroRepository transaccionRepository;
    private final EventPublisher eventPublisher;

    @Override
    public Transaccion ejecutar(TransaccionCobroCommand cmd) {
        // obtenemos la cuenta
        Cuenta cuenta = cuentaRepository.findById(cmd.cuentaId())
                .orElseThrow(() -> {
                    log.warn("Cuenta no encontrada: {}", cmd.cuentaId());
                    return new CuentaNoEncontradaException("Cuenta no encontrada");
                });
        // la moneda
        Moneda moneda = monedaRepository.findById(cmd.moneda())
                .orElseThrow(() -> {
                    log.warn("Moneda no encontrada: {}", cmd.moneda());
                    return new CuentaNoEncontradaException("Moneda no encontrada");
                });
        // Luego de la moneda hacemos la transaccion:
        var result = cuenta.debitar(cmd.monto());
        if (result.isFailure()) {
            log.warn("Error al debitar en cuenta [cuentaId={}, error={}]",
                    cmd.cuentaId(), result.getError().getMessage());
            throw new MontoInvalidoException(result.getError().getMessage());
        }

        DetalleTransaccion detalle = new DetalleCobro(cmd.razon());
        Transaccion cobro = Transaccion.cobro(cuenta.getId(),
                cmd.monto(),
                moneda,
                "Un cobro no asociado a un servicio especifico",
                detalle);
        log.info("Se va a guardar los siguientes datos:\n {} ,\n {}", cuenta, cobro);
        var tx = transaccionRepository.save(cobro);
        cuentaRepository.save(cuenta);
        // generamos el evento
        eventPublisher.publish(CobroRealizadoEvent.from(tx));
        log.info("Recarga exitosa [cuentaId={}, monto={}, transacciónId={}]",
                cmd.cuentaId(), cmd.monto(), cobro.getId());
        return tx;
    }
}
