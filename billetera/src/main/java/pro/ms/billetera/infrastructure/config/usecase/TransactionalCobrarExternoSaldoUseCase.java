package pro.ms.billetera.infrastructure.config.usecase;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.ms.billetera.application.dto.command_usecase.TransaccionCobroCommand;
import pro.ms.billetera.application.port.in.transaccion.CobrarTransaccionExternoUseCase;
import pro.ms.billetera.domain.model.Transaccion;

@Service
@Transactional
@Qualifier("transactionalCobrarExternoSaldoUseCase")
public class TransactionalCobrarExternoSaldoUseCase implements CobrarTransaccionExternoUseCase {

    private final CobrarTransaccionExternoUseCase cobrarSaldoUseCase;

    public TransactionalCobrarExternoSaldoUseCase(
            @Qualifier("cobrarTransaccionApplication") CobrarTransaccionExternoUseCase cobrarSaldoUseCase) {
        {
            this.cobrarSaldoUseCase = cobrarSaldoUseCase;
        }
    }

    @Override
    @Transactional
    public Transaccion ejecutar(TransaccionCobroCommand command) {
        return cobrarSaldoUseCase.ejecutar(command);
    }
}