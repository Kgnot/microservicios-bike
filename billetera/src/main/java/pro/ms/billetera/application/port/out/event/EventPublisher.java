package pro.ms.billetera.application.port.out.event;

public interface EventPublisher {

    void publish(Object event);
}
