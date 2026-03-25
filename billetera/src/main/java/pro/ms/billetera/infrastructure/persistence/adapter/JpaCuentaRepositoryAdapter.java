package pro.ms.billetera.infrastructure.persistence.adapter;

import org.springframework.stereotype.Repository;
import pro.ms.billetera.application.port.out.CuentaRepository;
import pro.ms.billetera.domain.model.Cuenta;

import java.util.UUID;

@Repository
public class JpaCuentaRepositoryAdapter implements CuentaRepository {
    @Override
    public Cuenta findById(UUID id) {
        return null;
    }

    @Override
    public Cuenta save(Cuenta cuenta) {
        return null;
    }
}
