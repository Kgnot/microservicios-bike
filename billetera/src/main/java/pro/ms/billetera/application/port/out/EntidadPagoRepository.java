package pro.ms.billetera.application.port.out;

import pro.ms.billetera.domain.model.EntidadPago;

public interface EntidadPagoRepository {

    EntidadPago findById(Short id);

}


