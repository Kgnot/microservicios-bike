package pro.ms.billetera.application.dto.command_usecase;

import java.math.BigDecimal;
import java.util.UUID;

public record TransaccionCobroCommand(
        UUID cuentaId,
        BigDecimal monto,
        String moneda, // moneda_id solo que estoy pendejo
        String razon

) {
}
