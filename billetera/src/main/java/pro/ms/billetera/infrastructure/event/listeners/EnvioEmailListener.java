package pro.ms.billetera.infrastructure.event.listeners;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import pro.ms.billetera.application.event.RecargaRealizadaEvent;
import pro.ms.billetera.domain.model.detalle_transaccion.DetalleRecarga;
import pro.ms.billetera.infrastructure.config.event.RabbitTransaccionRecargaConfig;
import pro.ms.billetera.infrastructure.event.message.RecargaRealizadaMessage;

import java.time.Instant;

@Slf4j
@Component
@RequiredArgsConstructor
public class EnvioEmailListener {

    public final RabbitTemplate rabbitTemplate;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleRecargaRealizada(RecargaRealizadaEvent event) {
        var detalle = (DetalleRecarga) event.getTransaccion().getDetalle();
        RecargaRealizadaMessage message = new RecargaRealizadaMessage(
                event.getTransaccion().getId(),
                event.getTransaccion().getMonto(),
                event.getTransaccion().getMoneda().getId(),
                event.getTransaccion().getCuentaId(),
                event.getTransaccion().getDescripcion(),
                detalle.getEntidad().getNombre(),
                detalle.getNumeroCuenta(),
                Instant.now()
        );
        log.info("Enviando email por recarga: {}", event.getTransaccion().getId());
        rabbitTemplate.convertAndSend(
                RabbitTransaccionRecargaConfig.EXCHANGE,
                RabbitTransaccionRecargaConfig.TRANSACCION_RECARGA_EXCHANGE,
                message
        );
    }
}