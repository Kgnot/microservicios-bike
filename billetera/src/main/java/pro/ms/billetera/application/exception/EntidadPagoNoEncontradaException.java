package pro.ms.billetera.application.exception;

import bike.common.error.AppException;
import bike.common.error.domain.BilleteraErrorCode;
import pro.ms.billetera.utils.context.BilleteraContext;

public class EntidadPagoNoEncontradaException extends AppException {

    public EntidadPagoNoEncontradaException(Short entidadPagoId) {
        super(BilleteraErrorCode.ENTIDAD_PAGO_NO_ENCONTRADA,
                BilleteraContext.PREFIX,
                BilleteraContext.MS_NAME,
                "Entidad de pago no encontrada con ID: " + entidadPagoId);
    }
}