package pro.ms.billetera.domain.model;

import lombok.*;
import pro.ms.billetera.domain.error.CuentaError;
import pro.ms.billetera.utils.shared.Result;

import java.math.BigDecimal;
import java.util.UUID;

/*
 * Esta es la cuenta que tiene un usuario
 * */
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Cuenta {

    private UUID id;
    private UUID usuarioId;
    private BigDecimal saldo;
    private Moneda moneda;

    /*
     * Debitar hace referencia a restar un monto
     * */
    public Result<Void, CuentaError> debitar(BigDecimal monto) {
        if (monto.compareTo(BigDecimal.ZERO) < 0) {
            return new Result.Failure<>(new CuentaError.MontoInvalido());
        }
        if (saldo.compareTo(monto) < 0) {
            return new Result.Failure<>(new CuentaError.SaldoInsuficiente());
        }
        this.saldo = saldo.subtract(monto);
        return new Result.Success<>(null);
    }

    /*
     * acreditar hace referencia a sumar un monto
     * */
    public Result<Void, CuentaError> acreditar(BigDecimal monto) {
        if (monto.compareTo(BigDecimal.ZERO) < 0) {
            return new Result.Failure<>(new CuentaError.MontoInvalido());
        }
        saldo = saldo.add(monto);
        return new Result.Success<>(null);
    }


}
