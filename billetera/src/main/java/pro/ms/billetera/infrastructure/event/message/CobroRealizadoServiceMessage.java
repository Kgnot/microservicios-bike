package pro.ms.billetera.infrastructure.event.message;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record CobroRealizadoServiceMessage(
        UUID id,
        BigDecimal monto,
        String moneda,
        UUID cuentaId,
        String descripcion,
        UUID servicioId,
        Instant occurredAt

) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;


}