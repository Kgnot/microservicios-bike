package pro.ms.auth.usecase.error;

public sealed interface LoginError {

    record UserNotFound(Object e) implements LoginError {
    }

    record EmailNotPresent(Object e) implements LoginError {
    }

    record UserInactive(Object e) implements LoginError {
    }

    record InvalidPassword(Object e) implements LoginError {
    }

    default String getMessage() {
        return switch (this) {
            case UserNotFound(Object key) -> "Usuario no encontrado con : " + key.toString();
            case EmailNotPresent(Object key) -> "El email no se encuentra presente en la solicitud: " + key.toString();
            case UserInactive(Object key) -> "El usuario esta inactivado con : " + key.toString();
            case InvalidPassword(Object key) -> "La contraseña es incorrecta para : " + key.toString();
        };
    }

}
