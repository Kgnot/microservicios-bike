package pro.ms.billetera.application.port.out;

import pro.ms.billetera.domain.model.Transaccion;

import java.util.Optional;

public interface TransaccionRepository {

    Optional<Transaccion> findById(Short id);

    Transaccion save(Transaccion transaccion);

}
