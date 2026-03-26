package pro.ms.billetera.utils.mapper.controller;

import pro.ms.billetera.application.dto.command_usecase.TransaccionServicioCommand;
import pro.ms.billetera.infrastructure.controller.request.TransaccionServicioRequest;

public class TransaccionServicioRequestMapper {
    private TransaccionServicioRequestMapper() {
    }

    public static TransaccionServicioCommand toTransaccionCobroCommand(TransaccionServicioRequest request) {
        return new TransaccionServicioCommand(
                request.servicioId(),
                request.cuentaId(),
                request.monto(),
                request.moneda()
        );
    }
}
