package pro.ms.billetera.application.port.in;

import pro.ms.billetera.application.dto.command_usecase.TransaccionCobroCommand;
import pro.ms.billetera.domain.model.Transaccion;

public interface CobrarTransaccionExternoUseCase {

    Transaccion execute(TransaccionCobroCommand cmd);
}
