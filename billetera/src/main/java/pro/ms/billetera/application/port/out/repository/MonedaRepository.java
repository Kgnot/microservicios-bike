package pro.ms.billetera.application.port.out.repository;

import pro.ms.billetera.domain.model.Moneda;

import java.util.List;
import java.util.Optional;

public interface MonedaRepository {

    Optional<Moneda> findById(String id);

    List<Moneda> findAll();

    Moneda save(Moneda moneda);

}
