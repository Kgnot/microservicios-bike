package pro.ms.billetera.application.port.out.repository;

import pro.ms.billetera.domain.model.Transaccion;

import java.util.Optional;
import java.util.UUID;

public interface TransaccionRepository {

    Optional<Transaccion> findById(UUID id);

    Transaccion save(Transaccion transaccion);

}
