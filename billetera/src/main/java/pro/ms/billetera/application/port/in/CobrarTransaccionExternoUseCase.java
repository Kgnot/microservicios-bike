package pro.ms.billetera.application.port.in;

import pro.ms.billetera.application.dto.command_usecase.TransaccionCobroCommand;
import pro.ms.billetera.application.error.TransaccionCobroError;
import pro.ms.billetera.domain.model.Transaccion;
import pro.ms.billetera.utils.shared.Result;

public interface CobrarTransaccionExternoUseCase {

    Result<Transaccion, TransaccionCobroError> execute(TransaccionCobroCommand cmd);
}
