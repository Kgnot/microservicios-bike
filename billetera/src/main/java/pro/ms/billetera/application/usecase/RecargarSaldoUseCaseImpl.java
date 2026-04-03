package pro.ms.billetera.application.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pro.ms.billetera.application.dto.command_usecase.RecargarCommand;
import pro.ms.billetera.application.event.RecargaRealizadaEvent;
import pro.ms.billetera.application.port.in.transaccion.RecargarSaldoUseCase;
import pro.ms.billetera.application.port.out.event.EventPublisher;
import pro.ms.billetera.application.port.out.repository.CuentaRepository;
import pro.ms.billetera.application.port.out.repository.TransaccionRecargaRepository;
import pro.ms.billetera.application.service.TransaccionEntityResolverService;
import pro.ms.billetera.application.service.TransaccionValidatorService;
import pro.ms.billetera.domain.model.Cuenta;
import pro.ms.billetera.domain.model.EntidadPago;
import pro.ms.billetera.domain.model.Moneda;
import pro.ms.billetera.domain.model.Transaccion;
import pro.ms.billetera.domain.model.detalle_transaccion.DetalleRecarga;

@Slf4j
@RequiredArgsConstructor
public class RecargarSaldoUseCaseImpl implements RecargarSaldoUseCase {

    private final TransaccionEntityResolverService resolver;
    private final TransaccionValidatorService validator;
    private final TransaccionRecargaRepository transaccionRecargaRepository;
    private final CuentaRepository cuentaRepository;
    private final EventPublisher eventPublisher;

    @Override
    public Transaccion ejecutar(RecargarCommand cmd) {
        Cuenta cuenta = resolver.obtenerCuenta(cmd.cuentaId());
        Moneda moneda = resolver.obtenerMoneda(cmd.moneda());
        EntidadPago ePago = resolver.obtenerEntidadPago(cmd.entidadPago());

        validator.validarMonedaCuenta(cuenta, moneda);
        validator.validarCredito(cuenta, cmd.monto());

        DetalleRecarga detalle = new DetalleRecarga(ePago, cmd.numeroCuenta());
        Transaccion tx = Transaccion.recarga(
                cuenta.getId(),
                cmd.monto(),
                moneda,
                cmd.descripcion(),
                detalle
        );

        Transaccion recargaSaved = transaccionRecargaRepository.save(tx);
        cuentaRepository.save(cuenta);
        eventPublisher.publish(RecargaRealizadaEvent.from(recargaSaved));
        log.debug("Cobro realizado: {}", tx);
        return recargaSaved;
    }
}
