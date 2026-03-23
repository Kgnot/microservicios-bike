package pro.ms.auth.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pro.ms.auth.configuration.RabbitUserRegisterConfig;
import pro.ms.auth.event.dto.UserRegisterMessage;

import java.time.Instant;

@Component
@Slf4j
@RequiredArgsConstructor
public class EmailEvents {

    public final RabbitTemplate rabbitTemplate;

    @EventListener
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

