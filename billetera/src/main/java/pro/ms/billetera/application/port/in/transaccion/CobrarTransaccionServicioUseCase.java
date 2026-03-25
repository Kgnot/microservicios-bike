package pro.ms.billetera.application.port.in.transaccion;

import pro.ms.billetera.application.dto.command_usecase.TransaccionServicioCommand;
import pro.ms.billetera.domain.model.Transaccion;

public interface CobrarTransaccionServicioUseCase {

    Transaccion ejecutar(TransaccionServicioCommand cmd);
}
