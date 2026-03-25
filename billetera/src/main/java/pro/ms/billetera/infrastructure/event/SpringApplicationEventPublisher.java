package pro.ms.billetera.infrastructure.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import pro.ms.billetera.application.port.out.event.EventPublisher;

@Component
@Slf4j
@RequiredArgsConstructor
public class SpringApplicationEventPublisher implements EventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void publish(Object event) {
        log.info("Publicando evento: {}", event.getClass().getSimpleName());
        applicationEventPublisher.publishEvent(event);
    }
}
