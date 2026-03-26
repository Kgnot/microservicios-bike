package pro.ms.billetera.infrastructure.config.usecase;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
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
    public Transaccion ejecutar(TransaccionServicioCommand cmd) {
        return cobrarTransaccionServicioUseCase.ejecutar(cmd);
    }
}
