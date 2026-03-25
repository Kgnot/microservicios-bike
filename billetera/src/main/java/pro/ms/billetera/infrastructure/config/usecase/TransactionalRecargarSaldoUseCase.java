package pro.ms.billetera.infrastructure.config.usecase;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.ms.billetera.application.dto.command_usecase.RecargarCommand;
import pro.ms.billetera.application.port.in.transaccion.RecargarSaldoUseCase;
import pro.ms.billetera.domain.model.Transaccion;

@Service
@Transactional
@Qualifier("transactionalRecargarSaldoUseCase")
public class TransactionalRecargarSaldoUseCase implements RecargarSaldoUseCase {

    private final RecargarSaldoUseCase recargarSaldoUseCase;

    public TransactionalRecargarSaldoUseCase(RecargarSaldoUseCase recargarSaldoUseCase) {
        this.recargarSaldoUseCase = recargarSaldoUseCase;
    }

    @Override
    @Transactional
    public Transaccion ejecutar(RecargarCommand command) {
        return recargarSaldoUseCase.ejecutar(command);
    }
}
