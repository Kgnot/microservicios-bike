package pro.ms.billetera.application.exception;

import bike.common.error.AppException;
import bike.common.error.domain.ServicioErrorCode;
import pro.ms.billetera.utils.context.BilleteraContext;

public class ServicioNoEncontrado extends AppException {

    public ServicioNoEncontrado(String detalle) {
        super(ServicioErrorCode.SERVICIO_NO_ENCONTRADO,
                BilleteraContext.PREFIX,
                BilleteraContext.MS_NAME,
                detalle);
    }
}
