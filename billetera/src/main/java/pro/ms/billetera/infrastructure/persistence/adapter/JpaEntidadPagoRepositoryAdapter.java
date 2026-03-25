package pro.ms.billetera.infrastructure.persistence.adapter;

import org.springframework.stereotype.Repository;
import pro.ms.billetera.application.port.out.EntidadPagoRepository;
import pro.ms.billetera.domain.model.EntidadPago;

import java.util.Optional;

@Repository
public class JpaEntidadPagoRepositoryAdapter implements EntidadPagoRepository {
    @Override
    public Optional<EntidadPago> findById(Short id) {
        return Optional.empty();
    }
}
