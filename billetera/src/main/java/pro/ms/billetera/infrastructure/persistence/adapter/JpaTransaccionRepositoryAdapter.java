package pro.ms.billetera.infrastructure.persistence.adapter;

import org.springframework.stereotype.Repository;
import pro.ms.billetera.application.port.out.TransaccionRepository;
import pro.ms.billetera.domain.model.Transaccion;

import java.util.Optional;

@Repository
public class JpaTransaccionRepositoryAdapter implements TransaccionRepository {
    @Override
    public Optional<Transaccion> findById(Short id) {
        return Optional.empty();
    }

    @Override
    public Transaccion save(Transaccion transaccion) {
        return null;
    }
}
