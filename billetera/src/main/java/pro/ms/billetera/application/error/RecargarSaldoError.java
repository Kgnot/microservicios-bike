package pro.ms.billetera.application.error;

import pro.ms.billetera.domain.error.CuentaError;

public sealed interface RecargarSaldoError {

    record CuentaNoEncontrada(String cuenta) implements RecargarSaldoError {
    }

    record UsuarioNoAutorizado(String usuario) implements RecargarSaldoError {
    }

    record EntidadPagoNoEncontrada(String entidadPago) implements RecargarSaldoError {
    }

    record MonedaNoEncontrada(String moneda) implements RecargarSaldoError {
    }

    record MonedaInvalida(String moneda) implements RecargarSaldoError {
    }

    record ErrorDominioCuenta(CuentaError ed) implements RecargarSaldoError {
    }

    record TransaccionNoEncontrada(String transaccion) implements RecargarSaldoError {
    }

    default String getMessage() {
        return switch (this) {
            case CuentaNoEncontrada(String cuenta) -> "Usuario no encontrado: " + cuenta;
            case UsuarioNoAutorizado(String usuario) -> "Usuario no Autorizado: " + usuario;
            case ErrorDominioCuenta(CuentaError ed) -> "Error Dominio: " + ed.getMessage();
            case MonedaNoEncontrada(String moneda) -> "Moneda no encontrada: " + moneda;
            case MonedaInvalida(String moneda) -> "Moneda no coincide con la cuenta: " + moneda;
            case TransaccionNoEncontrada(String transaccion) -> "Transacción no encontrada: " + transaccion;
            case EntidadPagoNoEncontrada(String entidad) -> "Entidad de pago no encontrada" + entidad;
        };
    }

}
