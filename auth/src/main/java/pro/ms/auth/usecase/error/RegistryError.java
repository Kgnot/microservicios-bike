package pro.ms.auth.usecase.error;

public sealed interface RegistryError {

    record ExternalServiceError(String message) implements RegistryError {
    }
    record EmailAlreadyExistsError(String message) implements RegistryError {
    }

}
