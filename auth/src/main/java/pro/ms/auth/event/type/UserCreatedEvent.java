package pro.ms.auth.event.type;

import java.io.Serial;
import java.io.Serializable;

public record UserCreatedEvent(
        String name,
        String email,
        Short rol
) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
}
