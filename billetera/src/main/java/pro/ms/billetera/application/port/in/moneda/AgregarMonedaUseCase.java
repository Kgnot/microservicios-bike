package pro.ms.billetera.application.port.in.moneda;

import pro.ms.billetera.domain.model.Moneda;

public interface AgregarMonedaUseCase {
    Moneda save(Moneda moneda);
}
