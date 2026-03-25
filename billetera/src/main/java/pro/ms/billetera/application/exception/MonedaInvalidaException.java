package pro.ms.billetera.application.exception;

import bike.common.error.AppException;
import bike.common.error.CommonErrorCode;
import pro.ms.billetera.utils.context.BilleteraContext;

public class MonedaInvalidaException extends AppException {

    public MonedaInvalidaException(String descripcionMoneda) {
        super(CommonErrorCode.MONEDA_NO_ENCONTRADA,//MONEDA_INVALIDA, - TODO: cambiar
                BilleteraContext.PREFIX,
                BilleteraContext.MS_NAME,
                "La moneda '" + descripcionMoneda + "' no corresponde a la cuenta");
    }
}