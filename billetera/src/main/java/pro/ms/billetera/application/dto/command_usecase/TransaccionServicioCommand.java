package pro.ms.billetera.application.dto.command_usecase;

import java.util.UUID;

public record TransaccionServicioCommand(
        UUID servicioId
) {
}
