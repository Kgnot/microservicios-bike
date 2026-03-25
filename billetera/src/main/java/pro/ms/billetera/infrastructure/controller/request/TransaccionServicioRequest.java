package pro.ms.billetera.infrastructure.controller.request;

import java.util.UUID;

public record TransaccionServicioRequest(
        UUID servicioId
) {
}

