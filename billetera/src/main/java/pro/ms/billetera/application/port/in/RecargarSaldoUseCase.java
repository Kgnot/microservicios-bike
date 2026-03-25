package pro.ms.billetera.application.port.in;

import pro.ms.billetera.application.dto.command_usecase.RecargarCommand;
import pro.ms.billetera.application.error.RecargarSaldoError;
import pro.ms.billetera.domain.model.Transaccion;
import pro.ms.billetera.utils.shared.Result;

public interface RecargarSaldoUseCase {

    Result<Transaccion, RecargarSaldoError> ejecutar(RecargarCommand command);
}
