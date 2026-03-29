package pro.ms.billetera.application.port.in.entidad_pago;

import pro.ms.billetera.domain.model.EntidadPago;

import java.util.List;

public interface ObtenerEntidadesPagoUseCase {

    List<EntidadPago> findAll();
}
