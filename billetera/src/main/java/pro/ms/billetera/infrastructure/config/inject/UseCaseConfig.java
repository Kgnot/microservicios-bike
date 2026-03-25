package pro.ms.billetera.infrastructure.config.inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pro.ms.billetera.application.port.in.RecargarSaldoUseCase;
import pro.ms.billetera.application.port.out.CuentaRepository;
import pro.ms.billetera.application.port.out.EntidadPagoRepository;
import pro.ms.billetera.application.port.out.MonedaRepository;
import pro.ms.billetera.application.port.out.TransaccionRepository;
import pro.ms.billetera.application.usecase.RecargarSaldoUseCaseImpl;

@Configuration
public class UseCaseConfig {

    @Bean
    public RecargarSaldoUseCase recargarSaldoUseCase(
            CuentaRepository cuentaRepository,
            TransaccionRepository transaccionRepository,
            MonedaRepository monedaRepository,
            EntidadPagoRepository entidadPagoRepository
    ) {
        return new RecargarSaldoUseCaseImpl(
                cuentaRepository,
                transaccionRepository,
                monedaRepository,
                entidadPagoRepository
        );
    }
}
