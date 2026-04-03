package pro.ms.billetera.infrastructure.config.inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pro.ms.billetera.application.port.in.entidad_pago.AgregarEntidadPagoUseCase;
import pro.ms.billetera.application.port.in.entidad_pago.ObtenerEntidadesPagoUseCase;
import pro.ms.billetera.application.port.in.transaccion.CobrarTransaccionExternoUseCase;
import pro.ms.billetera.application.port.in.transaccion.CobrarTransaccionServicioUseCase;
import pro.ms.billetera.application.port.in.transaccion.RecargarSaldoUseCase;
import pro.ms.billetera.application.port.out.event.EventPublisher;
import pro.ms.billetera.application.port.out.repository.*;
import pro.ms.billetera.application.service.TransaccionEntityResolverService;
import pro.ms.billetera.application.service.TransaccionValidatorService;
import pro.ms.billetera.application.usecase.CobrarTransaccionExternoUseCaseImpl;
import pro.ms.billetera.application.usecase.CobrarTransaccionServicioUseCaseImpl;
import pro.ms.billetera.application.usecase.RecargarSaldoUseCaseImpl;
import pro.ms.billetera.application.usecase.entidad_pago.AgregarEntidadPagoUseCaseImpl;
import pro.ms.billetera.application.usecase.entidad_pago.ObtenerEntidadesPagoUseCaseImpl;

@Configuration
public class UseCaseConfig {

    @Bean
    @Qualifier("recargarSaldoApplication")
    public RecargarSaldoUseCase recargarSaldoUseCase(
            TransaccionEntityResolverService resolverService,
            TransaccionValidatorService validatorService,
            TransaccionRecargaRepository transaccionRecargaRepository,
            CuentaRepository cuentaRepository,
            EventPublisher eventPublisher
    ) {
        return new RecargarSaldoUseCaseImpl(
                resolverService,
                validatorService,
                transaccionRecargaRepository,
                cuentaRepository,
                eventPublisher
        );
    }

    @Bean
    @Qualifier("cobrarTransaccionApplication")
    public CobrarTransaccionExternoUseCase cobrarTransaccionExternoUseCase(
            TransaccionEntityResolverService resolverService,
            TransaccionValidatorService validatorService,
            CuentaRepository cuentaRepository,
            TransaccionCobroRepository cobroRepository,
            EventPublisher eventPublisher
    ) {
        return new CobrarTransaccionExternoUseCaseImpl(
                resolverService,
                validatorService,
                cobroRepository,
                cuentaRepository,
                eventPublisher
        );
    }

    @Bean
    @Qualifier("agregarEntidadPagoUseCase")
    public AgregarEntidadPagoUseCase agregarEntidadPagoUseCase(
            EntidadPagoRepository repository
    ) {
        return new AgregarEntidadPagoUseCaseImpl(
                repository
        );
    }

    @Bean
    @Qualifier("cobrarTransaccionServicioApplication")
    public CobrarTransaccionServicioUseCase cobrarTransaccionServicioUseCase(
            TransaccionEntityResolverService resolverService,
            TransaccionValidatorService validatorService,
            TransaccionServicioRepository transaccionServicioRepository,
            CuentaRepository cuentaRepository,
            EventPublisher eventPublisher
    ) {
        return new CobrarTransaccionServicioUseCaseImpl(
                resolverService,
                validatorService,
                transaccionServicioRepository,
                cuentaRepository,
                eventPublisher
        );
    }



    @Bean
    @Qualifier("obtenerEntidadesPagoUseCase")
    public ObtenerEntidadesPagoUseCase obtenerEntidadesPagoUseCase(
            EntidadPagoRepository repository
    ) {
        return new ObtenerEntidadesPagoUseCaseImpl(
                repository
        );
    }
}
