package pro.ms.auth.event.dto;

import java.time.Instant;

public record UserRegisterMessage(
        String email,
        String nombre,
        String rol,
        Instant occurredAt
) {
}