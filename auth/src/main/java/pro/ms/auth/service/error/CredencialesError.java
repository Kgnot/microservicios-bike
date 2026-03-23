package pro.ms.auth.service.error;

public sealed interface CredencialesError {

    record NoSaveCredencialesError() implements CredencialesError {}


    default String getMessage(){
        return switch (this){
            case NoSaveCredencialesError() -> "No save credenciales";
        };
    }

}
