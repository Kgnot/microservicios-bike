package pro.ms.billetera.infrastructure.persistence.adapter;

import org.springframework.stereotype.Repository;
import pro.ms.billetera.application.port.out.MonedaRepository;
import pro.ms.billetera.domain.model.Moneda;

@Repository
public class JpaMonedaRepository implements MonedaRepository {
    @Override
    public Moneda findById(Short id) {
        return null;
    }
}
