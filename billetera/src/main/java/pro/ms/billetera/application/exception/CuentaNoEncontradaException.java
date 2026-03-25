package pro.ms.billetera.application.exception;

import bike.common.error.AppException;
import bike.common.error.CommonErrorCode;
import pro.ms.billetera.utils.context.BilleteraContext;

public class CuentaNoEncontradaException extends AppException {

    public CuentaNoEncontradaException() {
        super(CommonErrorCode.CUENTA_NO_ENCONTRADA,
                BilleteraContext.PREFIX,
                BilleteraContext.MS_NAME);
    }

    public CuentaNoEncontradaException(String customMessage) {
        super(CommonErrorCode.CUENTA_NO_ENCONTRADA,
                BilleteraContext.PREFIX,
                BilleteraContext.MS_NAME,
                customMessage);
    }
}
