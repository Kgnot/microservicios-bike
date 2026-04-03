package pro.ms.billetera.utils.mapper.controller;

import pro.ms.billetera.application.dto.command_usecase.RecargarCommand;
import pro.ms.billetera.infrastructure.controller.request.RecargarRequest;

public final class TransaccionRequestMapper {

    private TransaccionRequestMapper() {
    }

    public static RecargarCommand toRecargarCommand(RecargarRequest request) {
        if (request == null) {
            return null;
        }

        return new RecargarCommand(
                request.cuentaId(),
                request.monto(),
                request.moneda(),
                request.entidadPago(),
                request.descripcion(),
                request.numeroCuenta()
        );
    }

}

