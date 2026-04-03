package pro.ms.billetera.application.port.out.repository;

import pro.ms.billetera.domain.model.Cuenta;

import java.util.Optional;
import java.util.UUID;

public interface CuentaRepository {

    Optional<Cuenta> findById(UUID id);

    Cuenta save(Cuenta cuenta);
}
