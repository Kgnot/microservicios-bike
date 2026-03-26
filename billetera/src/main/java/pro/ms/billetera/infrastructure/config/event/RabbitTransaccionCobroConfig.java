package pro.ms.billetera.infrastructure.config.event;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitTransaccionCobroConfig {

    public static final String EXCHANGE = "bil.events";
    public static final String TRANSACCION_COBRO_QUEUE = "email.transaccion.cobro";
    public static final String TRANSACCION_COBRO_EXCHANGE = "transaccion.cobro";

    @Bean
    @Qualifier("cobro")
    TopicExchange billCobroExchange() {
        return new TopicExchange(EXCHANGE, true, false);
    }

    @Bean
    @Qualifier("cobro")
    Queue transaccionCobroQueue() {
        return new Queue(TRANSACCION_COBRO_QUEUE, true);
    }

    @Bean
    Binding transaccionCobroBinding(
            @Qualifier("cobro")
            Queue transaccionRecargaQueue,
            @Qualifier("cobro")
            TopicExchange transaccionRecargaExchange) {
        return BindingBuilder.bind(transaccionRecargaQueue)
                .to(transaccionRecargaExchange)
                .with(TRANSACCION_COBRO_EXCHANGE);
    }
}
