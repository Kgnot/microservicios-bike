package pro.ms.billetera.infrastructure.config.event;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitTransaccionServiceConfig{

    public static final String EXCHANGE = "bil.events";
    public static final String TRANSACCION_SERVICIO_QUEUE = "email.transaccion.servicio";
    public static final String TRANSACCION_SERVICIO_EXCHANGE = "transaccion.servicio";

    @Bean
    @Qualifier("servicio")
    TopicExchange billServicioExchange() {
        return new TopicExchange(EXCHANGE, true, false);
    }

    @Bean
    @Qualifier("servicio")
    Queue transaccionServicioQueue() {
        return new Queue(TRANSACCION_SERVICIO_QUEUE, true);
    }

    @Bean
    Binding transaccionServicioBinding(
            @Qualifier("servicio")
            Queue transaccionRecargaQueue,
            @Qualifier("servicio")
            TopicExchange transaccionRecargaExchange) {
        return BindingBuilder.bind(transaccionRecargaQueue)
                .to(transaccionRecargaExchange)
                .with(TRANSACCION_SERVICIO_EXCHANGE);
    }
}
