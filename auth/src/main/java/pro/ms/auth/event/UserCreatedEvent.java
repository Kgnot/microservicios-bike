package pro.ms.auth.event;

public record UserCreatedEvent(
        String name,
        String email,
        String rol
) {
}
