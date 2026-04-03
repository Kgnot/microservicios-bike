package pro.ms.billetera.infrastructure.config.event;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitTransaccionRecargaConfig {

    public static final String EXCHANGE = "bil.events";
    public static final String TRANSACCION_RECARGA_QUEUE = "email.transaccion.recarga";
    public static final String TRANSACCION_RECARGA_EXCHANGE = "transaccion.recarga";

    @Bean
    @Qualifier("recarga")
    TopicExchange billRecargaExchange() {
        return new TopicExchange(EXCHANGE, true, false);
    }

    @Bean
    @Qualifier("recarga")
    Queue transaccionRecargaQueue() {
        return new Queue(TRANSACCION_RECARGA_QUEUE, true);
    }

    @Bean
    Binding transaccionRecargaBinding(
            @Qualifier("recarga")
            Queue transaccionRecargaQueue,
            @Qualifier("recarga")
            TopicExchange transaccionRecargaExchange) {
        return BindingBuilder.bind(transaccionRecargaQueue)
                .to(transaccionRecargaExchange)
                .with(TRANSACCION_RECARGA_EXCHANGE);
    }
}



