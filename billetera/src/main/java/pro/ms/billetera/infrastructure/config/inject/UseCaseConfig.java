package pro.ms.billetera.infrastructure.config.inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pro.ms.billetera.application.port.in.RecargarSaldoUseCase;
import pro.ms.billetera.application.port.out.*;
import pro.ms.billetera.application.usecase.RecargarSaldoUseCaseImpl;

@Configuration
public class UseCaseConfig {

    @Bean
    public RecargarSaldoUseCase recargarSaldoUseCase(
            CuentaRepository cuentaRepository,
            TransaccionRepository transaccionRepository,
            TransaccionRecargaRepository transaccionRecargaRepository,
            MonedaRepository monedaRepository,
            EntidadPagoRepository entidadPagoRepository
    ) {
        return new RecargarSaldoUseCaseImpl(
                cuentaRepository,
                transaccionRepository,
                transaccionRecargaRepository,
                monedaRepository,
                entidadPagoRepository
        );
    }
}
