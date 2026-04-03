package pro.ms.billetera.infrastructure.controller.request;

import java.math.BigDecimal;
import java.util.UUID;

public record RecargarRequest(
        UUID cuentaId,
        BigDecimal monto,
        String moneda,
        Short entidadPago,
        String descripcion,
        String numeroCuenta
) {
}