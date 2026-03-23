package pro.ms.auth.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitUserRegisterConfig {

    public static final String EXCHANGE = "auth.events";
    public static final String USER_REGISTER_QUEUE = "email.user.register";
    public static final String USER_REGISTER_ROUTING_KEY = "user.register";

    @Bean
    TopicExchange authExchange() {
        return new TopicExchange(EXCHANGE,true,false);
    }

    @Bean
    Queue userRegisterQueue() {
        return new Queue(USER_REGISTER_QUEUE, true);
    }

    @Bean
    Binding userRegisterBinding(Queue userRegisterQueue, TopicExchange authExchange) {
        return BindingBuilder.bind(userRegisterQueue)
                .to(authExchange)
                .with(USER_REGISTER_ROUTING_KEY);
    }
}
