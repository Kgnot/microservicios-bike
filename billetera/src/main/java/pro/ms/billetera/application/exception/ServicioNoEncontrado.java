package pro.ms.billetera.application.exception;

import bike.common.error.AppException;
import bike.common.error.CommonErrorCode;
import pro.ms.billetera.utils.context.BilleteraContext;

public class ServicioNoEncontrado extends AppException {

    public ServicioNoEncontrado(String detalle) {
        super(CommonErrorCode.SALDO_INSUFICIENTE, // SERVICIO NO ENCONTRADO  TODO
                BilleteraContext.PREFIX,
                BilleteraContext.MS_NAME,
                detalle);
    }
}
