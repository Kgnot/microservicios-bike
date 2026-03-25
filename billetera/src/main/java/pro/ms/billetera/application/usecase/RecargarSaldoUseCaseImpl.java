package pro.ms.billetera.application.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pro.ms.billetera.application.dto.command_usecase.RecargarCommand;
import pro.ms.billetera.application.event.RecargaRealizadaEvent;
import pro.ms.billetera.application.exception.*;
import pro.ms.billetera.application.port.in.transaccion.RecargarSaldoUseCase;
import pro.ms.billetera.application.port.out.event.EventPublisher;
import pro.ms.billetera.application.port.out.repository.CuentaRepository;
import pro.ms.billetera.application.port.out.repository.EntidadPagoRepository;
import pro.ms.billetera.application.port.out.repository.MonedaRepository;
import pro.ms.billetera.application.port.out.repository.TransaccionRecargaRepository;
import pro.ms.billetera.domain.model.Cuenta;
import pro.ms.billetera.domain.model.EntidadPago;
import pro.ms.billetera.domain.model.Moneda;
import pro.ms.billetera.domain.model.Transaccion;
import pro.ms.billetera.domain.model.detalle_transaccion.DetalleRecarga;

@Slf4j
@RequiredArgsConstructor
public class RecargarSaldoUseCaseImpl implements RecargarSaldoUseCase {

    private final CuentaRepository cuentaRepository;
    private final TransaccionRecargaRepository transaccionRecargaRepository;
    private final MonedaRepository monedaRepository;
    private final EntidadPagoRepository entidadPagoRepository;
    private final EventPublisher eventPublisher;


    @Override
    public Transaccion ejecutar(RecargarCommand cmd) {

        Cuenta cuenta = cuentaRepository.findById(cmd.cuentaId())
                .orElseThrow(() -> {
                    log.warn("No existe la cuenta con id: {}", cmd.cuentaId());
                    return new CuentaNoEncontradaException(cmd.cuentaId() + "");
                });

        Moneda moneda = monedaRepository.findById(cmd.moneda())
                .orElseThrow(() -> {
                    log.warn("No existe la moneda con id: {}", cmd.moneda());
                    return new MonedaNoEncontradaException(cmd.moneda());
                });

        EntidadPago ePago = entidadPagoRepository.findById(cmd.entidadPago())
                .orElseThrow(() -> {
                    log.warn("No existe la entidad de pago con id: {}", cmd.entidadPago());
                    return new EntidadPagoNoEncontradaException(cmd.entidadPago());
                });

        if (!cuenta.getMoneda().equals(moneda)) {
            throw new MonedaInvalidaException(moneda.getDescripcion());
        }
        var acreditar = cuenta.acreditar(cmd.monto());
        //acreditamos y verificamos que no haya error
        if (acreditar.isFailure()) {
            log.warn("Error al acreditar en cuenta [cuentaId={}, error={}]",
                    cmd.cuentaId(), acreditar.getError());
            throw new SaldoInsuficienteException(acreditar.getError().getMessage());
        }

        DetalleRecarga detalle = new DetalleRecarga(
                ePago,
                cmd.numeroCuenta()
        );
        log.info("Detalle : \n {}", detalle);

        Transaccion tx = Transaccion.recarga(
                cuenta.getId(),
                cmd.monto(),
                moneda,
                cmd.descripcion(),
                detalle);

        log.info("Se va a guardar los siguientes datos:\n {} ,\n {}", cuenta, tx);
        Transaccion recargaSaved = transaccionRecargaRepository.save(tx);
        cuentaRepository.save(cuenta);
        // se genera la publicación del evento de recarga realizada
        eventPublisher.publish(RecargaRealizadaEvent.from(recargaSaved));
        log.info("Recarga exitosa [cuentaId={}, monto={}, transacciónId={}]",
                cmd.cuentaId(), cmd.monto(), recargaSaved.getId());

        return recargaSaved;
    }
}