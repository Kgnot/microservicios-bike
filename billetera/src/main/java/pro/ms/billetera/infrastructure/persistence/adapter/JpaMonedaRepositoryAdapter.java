package pro.ms.billetera.infrastructure.persistence.adapter;

import org.springframework.stereotype.Repository;
import pro.ms.billetera.application.port.out.MonedaRepository;
import pro.ms.billetera.domain.model.Moneda;

import java.util.Optional;

@Repository
public class JpaMonedaRepositoryAdapter implements MonedaRepository {
    @Override
    public Optional<Moneda> findById(Short id) {
        return Optional.empty();
    }

}
