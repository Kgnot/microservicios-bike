package pro.ms.billetera.application.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pro.ms.billetera.application.dto.command_usecase.TransaccionServicioCommand;
import pro.ms.billetera.application.event.CobroServicioRealizadoEvent;
import pro.ms.billetera.application.exception.CuentaNoEncontradaException;
import pro.ms.billetera.application.exception.MontoInvalidoException;
import pro.ms.billetera.application.port.in.transaccion.CobrarTransaccionServicioUseCase;
import pro.ms.billetera.application.port.out.event.EventPublisher;
import pro.ms.billetera.application.port.out.repository.CuentaRepository;
import pro.ms.billetera.application.port.out.repository.MonedaRepository;
import pro.ms.billetera.application.port.out.repository.TransaccionServicioRepository;
import pro.ms.billetera.domain.model.Cuenta;
import pro.ms.billetera.domain.model.Moneda;
import pro.ms.billetera.domain.model.Transaccion;
import pro.ms.billetera.domain.model.detalle_transaccion.DetalleServicio;
import pro.ms.billetera.domain.model.detalle_transaccion.DetalleTransaccion;

@Slf4j
@RequiredArgsConstructor
public class CobrarTransaccionServicioUseCaseImpl implements CobrarTransaccionServicioUseCase {


    private final CuentaRepository cuentaRepository;
    private final MonedaRepository monedaRepository;
    private final TransaccionServicioRepository transaccionRepository;
    private final EventPublisher eventPublisher;

    @Override
    public Transaccion ejecutar(TransaccionServicioCommand cmd) {
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

        DetalleTransaccion detalle = new DetalleServicio(cmd.servicioId());
        Transaccion cobro = Transaccion.cobro(cuenta.getId(),
                cmd.monto(),
                moneda,
                "Un cobro no asociado a un servicio especifico",
                detalle);
        log.info("Se va a guardar los siguientes datos:\n {} ,\n {}", cuenta, cobro);
        var tx = transaccionRepository.save(cobro);
        cuentaRepository.save(cuenta);
        // generamos el evento
        eventPublisher.publish(CobroServicioRealizadoEvent.from(tx));
        log.info("Recarga exitosa [cuentaId={}, monto={}, transacciónId={}]",
                cmd.cuentaId(), cmd.monto(), cobro.getId());
        return tx;
    }
}