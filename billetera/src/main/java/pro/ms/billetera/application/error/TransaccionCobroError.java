package pro.ms.billetera.application.error;

import pro.ms.billetera.domain.error.CuentaError;

public sealed interface TransaccionCobroError {

    record ErrorDominioCuenta(CuentaError ed) implements TransaccionCobroError {
    }

    record TransaccionNoEncontrada(String transaccion) implements TransaccionCobroError {
    }

    default String getMessage() {
        return switch (this) {
            case ErrorDominioCuenta(CuentaError ed) -> "Error Dominio: " + ed.getMessage();
            case TransaccionNoEncontrada(String transaccion) -> "Transacción no encontrada: " + transaccion;
        };
    }


}
