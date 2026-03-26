package pro.ms.billetera.infrastructure.config.inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pro.ms.billetera.application.port.in.transaccion.CobrarTransaccionExternoUseCase;
import pro.ms.billetera.application.port.in.transaccion.RecargarSaldoUseCase;
import pro.ms.billetera.application.port.out.event.EventPublisher;
import pro.ms.billetera.application.port.out.repository.*;
import pro.ms.billetera.application.usecase.CobrarTransaccionExternoUseCaseImpl;
import pro.ms.billetera.application.usecase.RecargarSaldoUseCaseImpl;

@Configuration
public class UseCaseConfig {

    @Bean
    @Qualifier("recargarSaldoApplication")
    public RecargarSaldoUseCase recargarSaldoUseCase(
            CuentaRepository cuentaRepository,
            TransaccionRecargaRepository transaccionRecargaRepository,
            MonedaRepository monedaRepository,
            EntidadPagoRepository entidadPagoRepository,
            EventPublisher eventPublisher
    ) {
        return new RecargarSaldoUseCaseImpl(
                cuentaRepository,
                transaccionRecargaRepository,
                monedaRepository,
                entidadPagoRepository,
                eventPublisher
        );
    }

    @Bean
    @Qualifier("cobrarTransaccionApplication")
    public CobrarTransaccionExternoUseCase cobrarTransaccionExternoUseCase(
            CuentaRepository cuentaRepository,
            TransaccionCobroRepository cobroRepository,
            MonedaRepository monedaRepository,
            EventPublisher eventPublisher
    ) {
        return new CobrarTransaccionExternoUseCaseImpl(
                cuentaRepository,
                monedaRepository,
                cobroRepository,
                eventPublisher
        );
    }
}
