package pro.ms.billetera.application.port.out.repository;

import pro.ms.billetera.domain.model.Transaccion;

public interface TransaccionRecargaRepository {

    Transaccion save(Transaccion transaccion);

}
