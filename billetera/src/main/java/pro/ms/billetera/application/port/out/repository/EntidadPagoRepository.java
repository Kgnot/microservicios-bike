package pro.ms.billetera.application.port.out.repository;

import pro.ms.billetera.domain.model.EntidadPago;

import java.util.List;
import java.util.Optional;

public interface EntidadPagoRepository {

    EntidadPago save(EntidadPago entidadPago);

    Optional<EntidadPago> findById(Short id);

    List<EntidadPago> findAll();
}


