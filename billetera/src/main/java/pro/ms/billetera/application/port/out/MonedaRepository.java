package pro.ms.billetera.application.port.out;

import pro.ms.billetera.domain.model.Moneda;

public interface MonedaRepository {

    Moneda findById(Short id);

}
