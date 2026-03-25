package pro.ms.billetera.application.port.out;

import pro.ms.billetera.domain.model.EntidadPago;

import java.util.Optional;

public interface EntidadPagoRepository {

    Optional<EntidadPago> findById(Short id);

}


