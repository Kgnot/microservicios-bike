package pro.ms.billetera.application.port.in.moneda;

import pro.ms.billetera.domain.model.Moneda;

import java.util.List;

public interface ObtenerMonedasUseCase {

    List<Moneda> obtenerMonedas();

}
