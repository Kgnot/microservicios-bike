package pro.ms.billetera.application.port.in;

import pro.ms.billetera.application.dto.command_usecase.TransaccionServicioCommand;
import pro.ms.billetera.application.error.TransaccionServicioError;
import pro.ms.billetera.domain.model.Transaccion;
import pro.ms.billetera.utils.shared.Result;

public interface CobrarTransaccionServicioUseCase {

    Result<Transaccion, TransaccionServicioError> ejecutar(TransaccionServicioCommand cmd);
}
