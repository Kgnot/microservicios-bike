package pro.ms.auth.service.error;

public sealed interface RolError {

    record RolNotFoundError(Short id) implements RolError {
    }

    default String getMessage(RolError error) {
        return switch (this) {
            case RolNotFoundError(Short id) -> "No se encontró el rol con id: " + id;
        };
    }

}
