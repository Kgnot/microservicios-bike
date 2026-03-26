package pro.ms.billetera.application.exception;

import bike.common.error.AppException;
import bike.common.error.domain.BilleteraErrorCode;
import pro.ms.billetera.utils.context.BilleteraContext;

public class MontoInvalidoException extends AppException {
    public MontoInvalidoException(String message) {
        super(
                BilleteraErrorCode.MONTO_INVALIDO,
                BilleteraContext.PREFIX,
                BilleteraContext.MS_NAME,
                message
        );
    }
}
