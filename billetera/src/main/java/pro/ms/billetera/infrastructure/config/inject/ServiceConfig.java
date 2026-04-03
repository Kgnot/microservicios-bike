package pro.ms.billetera.infrastructure.config.inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pro.ms.billetera.application.port.out.repository.CuentaRepository;
import pro.ms.billetera.application.port.out.repository.EntidadPagoRepository;
import pro.ms.billetera.application.port.out.repository.MonedaRepository;
import pro.ms.billetera.application.service.TransaccionEntityResolverService;
import pro.ms.billetera.application.service.TransaccionValidatorService;

@Configuration
public class ServiceConfig {

    @Bean
    public TransaccionEntityResolverService transaccionEntityResolverService(
            CuentaRepository cuentaRepository,
            MonedaRepository monedaRepository,
            EntidadPagoRepository entidadPagoRepository
    ) {
        return new TransaccionEntityResolverService(cuentaRepository,
                monedaRepository, entidadPagoRepository);
    }

    @Bean
    public TransaccionValidatorService transaccionValidationService(
    ) {
        return new TransaccionValidatorService();
    }
}
