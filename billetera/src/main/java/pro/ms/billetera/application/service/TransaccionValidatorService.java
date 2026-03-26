package pro.ms.billetera.application.service;

import lombok.extern.slf4j.Slf4j;
import pro.ms.billetera.application.exception.MonedaInvalidaException;
import pro.ms.billetera.application.exception.MontoInvalidoException;
import pro.ms.billetera.application.exception.SaldoInsuficienteException;
import pro.ms.billetera.domain.model.Cuenta;
import pro.ms.billetera.domain.model.Moneda;

import java.math.BigDecimal;

@Slf4j
public class TransaccionValidatorService {

    public void validarMonedaCuenta(Cuenta cuenta, Moneda moneda) {
        log.debug("Validando moneda {} contra cuenta {}", moneda.getDescripcion(), cuenta.getId());
        if (!cuenta.getMoneda().equals(moneda)) {
            log.warn("Moneda inválida: cuenta opera en {} pero se recibió {}",
                    cuenta.getMoneda().getDescripcion(), moneda.getDescripcion());
            throw new MonedaInvalidaException(moneda.getDescripcion());
        }
    }

    public void validarDebito(Cuenta cuenta, BigDecimal monto) {
        log.debug("Validando débito de {} en cuenta {}", monto, cuenta.getId());
        var result = cuenta.debitar(monto);
        if (result.isFailure()) {
            log.warn("Débito inválido en cuenta {}: {}", cuenta.getId(), result.getError().getMessage());
            throw new MontoInvalidoException(result.getError().getMessage());
        }
    }

    public void validarCredito(Cuenta cuenta, BigDecimal monto) {
        log.debug("Validando crédito de {} en cuenta {}", monto, cuenta.getId());
        var result = cuenta.acreditar(monto);
        if (result.isFailure()) {
            log.warn("Crédito inválido en cuenta {}: {}", cuenta.getId(), result.getError().getMessage());
            throw new SaldoInsuficienteException(result.getError().getMessage());
        }
    }
}