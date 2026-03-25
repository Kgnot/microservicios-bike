package pro.ms.billetera.application.port.in;

import pro.ms.billetera.application.dto.command_usecase.RecargarCommand;
import pro.ms.billetera.domain.model.Transaccion;

public interface RecargarSaldoUseCase {

    Transaccion ejecutar(RecargarCommand command);
}
