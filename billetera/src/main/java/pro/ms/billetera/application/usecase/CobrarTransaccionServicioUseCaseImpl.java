package pro.ms.billetera.application.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pro.ms.billetera.application.dto.command_usecase.TransaccionServicioCommand;
import pro.ms.billetera.application.event.CobroServicioRealizadoEvent;
import pro.ms.billetera.application.port.in.transaccion.CobrarTransaccionServicioUseCase;
import pro.ms.billetera.application.port.out.event.EventPublisher;
import pro.ms.billetera.application.port.out.repository.CuentaRepository;
import pro.ms.billetera.application.port.out.repository.TransaccionServicioRepository;
import pro.ms.billetera.application.service.TransaccionEntityResolverService;
import pro.ms.billetera.application.service.TransaccionValidatorService;
import pro.ms.billetera.domain.model.Cuenta;
import pro.ms.billetera.domain.model.Moneda;
import pro.ms.billetera.domain.model.Transaccion;
import pro.ms.billetera.domain.model.detalle_transaccion.DetalleServicio;
import pro.ms.billetera.domain.model.detalle_transaccion.DetalleTransaccion;

@Slf4j
@RequiredArgsConstructor
public class CobrarTransaccionServicioUseCaseImpl implements CobrarTransaccionServicioUseCase {

    private final TransaccionEntityResolverService resolver;
    private final TransaccionValidatorService validator;
    private final TransaccionServicioRepository transaccionRepository;
    private final CuentaRepository cuentaRepository;
    private final EventPublisher eventPublisher;

    @Override
    public Transaccion ejecutar(TransaccionServicioCommand cmd) {
        Cuenta cuenta = resolver.obtenerCuenta(cmd.cuentaId());
        Moneda moneda = resolver.obtenerMoneda(cmd.moneda());
        validator.validarDebito(cuenta, cmd.monto());

        DetalleTransaccion detalle = new DetalleServicio(cmd.servicioId());
        Transaccion cobro = Transaccion.cobro(
                cuenta.getId(),
                cmd.monto(),
                moneda,
                "Un cobro no asociado a un servicio especifico",
                detalle
        );

        var tx = transaccionRepository.save(cobro);
        cuentaRepository.save(cuenta);
        eventPublisher.publish(CobroServicioRealizadoEvent.from(tx));
        log.debug("Cobro realizado: {}", tx);
        return tx;
    }
}
