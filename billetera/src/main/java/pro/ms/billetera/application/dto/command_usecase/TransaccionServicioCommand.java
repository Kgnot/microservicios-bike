package pro.ms.billetera.application.dto.command_usecase;

import java.math.BigDecimal;
import java.util.UUID;

public record TransaccionServicioCommand(
        UUID servicioId,
        UUID cuentaId,
        BigDecimal monto,
        String moneda // moneda_id solo que estoy pendejo
) {
}
