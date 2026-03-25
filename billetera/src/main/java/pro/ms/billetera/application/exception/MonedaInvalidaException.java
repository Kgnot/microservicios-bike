package pro.ms.billetera.application.exception;

import bike.common.error.AppException;
import bike.common.error.domain.BilleteraErrorCode;
import pro.ms.billetera.utils.context.BilleteraContext;

public class MonedaInvalidaException extends AppException {

    public MonedaInvalidaException(String descripcionMoneda) {
        super(BilleteraErrorCode.MONEDA_INVALIDA,
                BilleteraContext.PREFIX,
                BilleteraContext.MS_NAME,
                "La moneda '" + descripcionMoneda + "' no corresponde a la cuenta");
    }
}