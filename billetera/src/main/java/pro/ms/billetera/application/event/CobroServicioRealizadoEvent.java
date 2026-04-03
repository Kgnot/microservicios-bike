package pro.ms.billetera.application.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pro.ms.billetera.domain.model.Transaccion;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@RequiredArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class CobroServicioRealizadoEvent {
    private final UUID eventId = UUID.randomUUID();
    private final LocalDateTime fecha = LocalDateTime.now();
    private final Transaccion transaccion;

    public static CobroServicioRealizadoEvent from(Transaccion transaccion) {
        return new CobroServicioRealizadoEvent(
                transaccion
        );
    }
}