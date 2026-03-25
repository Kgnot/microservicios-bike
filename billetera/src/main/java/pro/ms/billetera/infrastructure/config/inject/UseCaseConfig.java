package pro.ms.billetera.infrastructure.config.inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pro.ms.billetera.application.port.in.transaccion.RecargarSaldoUseCase;
import pro.ms.billetera.application.port.out.event.EventPublisher;
import pro.ms.billetera.application.port.out.repository.CuentaRepository;
import pro.ms.billetera.application.port.out.repository.EntidadPagoRepository;
import pro.ms.billetera.application.port.out.repository.MonedaRepository;
import pro.ms.billetera.application.port.out.repository.TransaccionRecargaRepository;
import pro.ms.billetera.application.usecase.RecargarSaldoUseCaseImpl;

@Configuration
public class UseCaseConfig {

    @Bean
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
}
