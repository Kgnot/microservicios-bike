package pro.ms.billetera.domain.model.detalle_transaccion;

import java.util.UUID;

public record DetalleServicio(UUID servicioId) implements DetalleTransaccion {
}
