package pro.ms.billetera.application.error;

public sealed interface TransaccionServicioError {

    record ServicioNoEncontrado(String servicio) implements TransaccionServicioError {
    }

    record TransaccionNoEncontrada(String transaccion) implements TransaccionServicioError {
    }

    default String getMessage() {
        return switch (this) {
            case ServicioNoEncontrado(String servicio) -> "Servicio no encontrado: " + servicio;
            case TransaccionNoEncontrada(String transaccion) -> "Transacción no encontrada: " + transaccion;
        };
    }
}