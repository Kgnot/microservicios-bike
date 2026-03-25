package pro.ms.billetera.domain.error;

public sealed interface CuentaError {
    record SaldoInsuficiente() implements CuentaError {
    }

    record MontoInvalido() implements CuentaError {
    }

    default String getMessage() {
        return switch (this) {
            case SaldoInsuficiente s -> "Saldo insuficiente para realizar la operación.";
            case MontoInvalido m -> "El monto especificado es inválido.";
        };
    }
}
