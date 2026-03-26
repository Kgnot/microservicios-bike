package pro.ms.billetera.infrastructure.controller.request;

import java.math.BigDecimal;
import java.util.UUID;

public record TransaccionCobroRequest(
        UUID cuentaId,
        BigDecimal monto,
        String moneda, // moneda_id solo que estoy pendejo
        String razon
) {
}

