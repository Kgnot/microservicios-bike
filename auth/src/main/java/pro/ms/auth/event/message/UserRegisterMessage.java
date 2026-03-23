package pro.ms.auth.event.message;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

public record UserRegisterMessage(
        String email,
        String nombre,
        Short rol,
        Instant occurredAt
) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
}