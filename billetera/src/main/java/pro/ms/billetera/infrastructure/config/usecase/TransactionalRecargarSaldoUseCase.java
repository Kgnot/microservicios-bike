package pro.ms.billetera.infrastructure.config.usecase;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.ms.billetera.application.dto.command_usecase.RecargarCommand;
import pro.ms.billetera.application.error.RecargarSaldoError;
import pro.ms.billetera.application.port.in.RecargarSaldoUseCase;
import pro.ms.billetera.domain.model.Transaccion;
import pro.ms.billetera.utils.shared.Result;

@Service
@Transactional
public class TransactionalRecargarSaldoUseCase implements RecargarSaldoUseCase {

    private final RecargarSaldoUseCase recargarSaldoUseCase;

    public TransactionalRecargarSaldoUseCase(RecargarSaldoUseCase recargarSaldoUseCase) {
        this.recargarSaldoUseCase = recargarSaldoUseCase;
    }

    @Override
    public Result<Transaccion, RecargarSaldoError> ejecutar(RecargarCommand command) {
        return recargarSaldoUseCase.ejecutar(command);
    }
}
