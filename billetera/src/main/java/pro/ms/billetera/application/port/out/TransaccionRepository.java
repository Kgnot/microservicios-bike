package pro.ms.billetera.application.port.out;

import pro.ms.billetera.domain.model.Transaccion;

public interface TransaccionRepository {

    Transaccion findById(Short id);

    Transaccion save(Transaccion transaccion);

}
