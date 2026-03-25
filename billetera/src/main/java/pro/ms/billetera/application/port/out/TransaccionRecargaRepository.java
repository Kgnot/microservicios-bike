package pro.ms.billetera.application.port.out;

import pro.ms.billetera.domain.model.Transaccion;

public interface TransaccionRecargaRepository {

    Transaccion save(Transaccion transaccion);

}
