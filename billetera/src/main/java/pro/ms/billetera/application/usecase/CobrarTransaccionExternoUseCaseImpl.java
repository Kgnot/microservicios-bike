package pro.ms.billetera.application.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pro.ms.billetera.application.dto.command_usecase.TransaccionCobroCommand;
import pro.ms.billetera.application.event.CobroRealizadoEvent;
import pro.ms.billetera.application.port.in.transaccion.CobrarTransaccionExternoUseCase;
import pro.ms.billetera.application.port.out.event.EventPublisher;
import pro.ms.billetera.application.port.out.repository.CuentaRepository;
import pro.ms.billetera.application.port.out.repository.TransaccionCobroRepository;
import pro.ms.billetera.application.service.TransaccionEntityResolverService;
import pro.ms.billetera.application.service.TransaccionValidatorService;
import pro.ms.billetera.domain.model.Cuenta;
import pro.ms.billetera.domain.model.Moneda;
import pro.ms.billetera.domain.model.Transaccion;
import pro.ms.billetera.domain.model.detalle_transaccion.DetalleCobro;
import pro.ms.billetera.domain.model.detalle_transaccion.DetalleTransaccion;

@Slf4j
@RequiredArgsConstructor
public class CobrarTransaccionExternoUseCaseImpl implements CobrarTransaccionExternoUseCase {

    private final TransaccionEntityResolverService resolver;
    private final TransaccionValidatorService validator;
    private final TransaccionCobroRepository transaccionRepository;
    private final CuentaRepository cuentaRepository;
    private final EventPublisher eventPublisher;

    @Override
    public Transaccion ejecutar(TransaccionCobroCommand cmd) {
        Cuenta cuenta = resolver.obtenerCuenta(cmd.cuentaId());
        Moneda moneda = resolver.obtenerMoneda(cmd.moneda());
        validator.validarDebito(cuenta, cmd.monto());

        DetalleTransaccion detalle = new DetalleCobro(cmd.razon());
        Transaccion cobro = Transaccion.cobro(
                cuenta.getId(),
                cmd.monto(),
                moneda,
                "Un cobro no asociado a un servicio especifico",
                detalle
        );

        var tx = transaccionRepository.save(cobro);
        cuentaRepository.save(cuenta);
        eventPublisher.publish(CobroRealizadoEvent.from(tx));
        log.debug("Cobro realizado: {}", tx);
        return tx;
    }
}
