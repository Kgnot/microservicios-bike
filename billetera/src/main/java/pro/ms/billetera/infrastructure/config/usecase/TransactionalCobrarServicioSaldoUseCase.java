package pro.ms.billetera.infrastructure.config.usecase;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pro.ms.billetera.application.dto.command_usecase.TransaccionServicioCommand;
import pro.ms.billetera.application.port.in.transaccion.CobrarTransaccionServicioUseCase;
import pro.ms.billetera.domain.model.Transaccion;

@Qualifier("transactionalCobrarServicioSaldoUseCase")
@Component
public class TransactionalCobrarServicioSaldoUseCase implements CobrarTransaccionServicioUseCase {

    private final CobrarTransaccionServicioUseCase cobrarTransaccionServicioUseCase;

    public TransactionalCobrarServicioSaldoUseCase(
            @Qualifier("cobrarTransaccionServicioApplication") CobrarTransaccionServicioUseCase cobrarTransaccionServicioUseCase) {
        this.cobrarTransaccionServicioUseCase = cobrarTransaccionServicioUseCase;
    }

    @Override
    @Transactional
    public Transaccion ejecutar(TransaccionServicioCommand cmd) {
        return cobrarTransaccionServicioUseCase.ejecutar(cmd);
    }
}
