package pro.ms.auth.event.email;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import pro.ms.auth.configuration.RabbitUserRegisterConfig;
import pro.ms.auth.event.message.UserRegisterMessage;
import pro.ms.auth.event.type.UserCreatedEvent;

import java.time.Instant;

@Component
@Slf4j
@RequiredArgsConstructor
public class EmailEventsRabbitMQ implements EmailEvents {

    public final RabbitTemplate rabbitTemplate;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onUserCreated(UserCreatedEvent event) {
        UserRegisterMessage message = new UserRegisterMessage(
                event.email(),
                event.name(),
                event.rol(),
                Instant.now()
        );

        rabbitTemplate.convertAndSend(
                RabbitUserRegisterConfig.EXCHANGE,
                RabbitUserRegisterConfig.USER_REGISTER_ROUTING_KEY,
                message
        );

        log.info("Evento publicado en RabbitMQ: routingKey={}, email={}",
                RabbitUserRegisterConfig.USER_REGISTER_ROUTING_KEY,
                event.email());
    }
}

