package pro.ms.billetera.application.port.out;

import pro.ms.billetera.domain.model.Cuenta;

import java.util.UUID;

public interface CuentaRepository {

    Cuenta findById(UUID id);

    Cuenta save(Cuenta cuenta);
}
