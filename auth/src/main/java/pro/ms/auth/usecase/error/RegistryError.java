package pro.ms.auth.usecase.error;

public sealed interface RegistryError {

    record ExternalServiceError(String message) implements RegistryError {
    }

    record EmailAlreadyExistsError(String message) implements RegistryError {
    }

    record RolNotFoundError(Short id) implements RegistryError {
    }

    default String getMessage() {
        return switch (this) {
            case ExternalServiceError(String message) -> "Error en el servicio externo: " + message;
            case EmailAlreadyExistsError(String message) -> "El email ya existe: " + message;
            case RolNotFoundError(Short id) -> "El rol no existe: " + id;
        };
    }
}
