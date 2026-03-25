package pro.ms.billetera.infrastructure.persistence.adapter;

import org.springframework.stereotype.Repository;
import pro.ms.billetera.application.port.out.EntidadPagoRepository;
import pro.ms.billetera.domain.model.EntidadPago;

@Repository
public class JpaEntidadPagoRepository implements EntidadPagoRepository {
    @Override
    public EntidadPago findById(Short id) {
        return null;
    }
}
