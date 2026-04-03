package pro.ms.billetera.application.exception;

import bike.common.error.AppException;
import bike.common.error.domain.BilleteraErrorCode;
import pro.ms.billetera.utils.context.BilleteraContext;

public class SaldoInsuficienteException extends AppException {

    public SaldoInsuficienteException(String detalle) {
        super(BilleteraErrorCode.SALDO_INSUFICIENTE,
                BilleteraContext.PREFIX,
                BilleteraContext.MS_NAME,
                detalle);
    }
}
