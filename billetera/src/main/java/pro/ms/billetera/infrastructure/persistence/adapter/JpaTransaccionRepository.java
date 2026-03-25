package pro.ms.billetera.infrastructure.persistence.adapter;

import org.springframework.stereotype.Repository;
import pro.ms.billetera.application.port.out.TransaccionRepository;
import pro.ms.billetera.domain.model.Transaccion;

@Repository
public class JpaTransaccionRepository implements TransaccionRepository {
    @Override
    public Transaccion findById(Short id) {
        return null;
    }

    @Override
    public Transaccion save(Transaccion transaccion) {
        return null;
    }
}
