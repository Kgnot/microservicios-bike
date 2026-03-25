package pro.ms.billetera.application.port.out;

import pro.ms.billetera.domain.model.Moneda;

import java.util.Optional;

public interface MonedaRepository {

    Optional<Moneda> findById(String id);

}
