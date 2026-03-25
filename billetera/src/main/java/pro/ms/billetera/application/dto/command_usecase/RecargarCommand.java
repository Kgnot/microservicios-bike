package pro.ms.billetera.application.dto.command_usecase;

import java.math.BigDecimal;
import java.util.UUID;

public record RecargarCommand(
        UUID cuentaId,
        BigDecimal monto,
        Short monedaId,
        Short entidadPago,
        String descripcion,
        String numeroCuenta
) {
}
