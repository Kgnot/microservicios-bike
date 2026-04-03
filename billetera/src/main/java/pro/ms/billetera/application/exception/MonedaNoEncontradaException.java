package pro.ms.billetera.application.exception;

import bike.common.error.AppException;
import bike.common.error.domain.BilleteraErrorCode;
import pro.ms.billetera.utils.context.BilleteraContext;

public class MonedaNoEncontradaException extends AppException {

    public MonedaNoEncontradaException(String monedaId) {
        super(BilleteraErrorCode.MONEDA_NO_ENCONTRADA,
                BilleteraContext.PREFIX,
                BilleteraContext.MS_NAME,
                "Moneda no encontrada con ID: " + monedaId);
    }
}