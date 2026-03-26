package pro.ms.billetera.infrastructure.event.message;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record CobroRealizadoMessage(
        UUID id,
        BigDecimal monto,
        String moneda,
        UUID cuentaId,
        String descripcion,
        String razon,
        Instant occurredAt

) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;


}