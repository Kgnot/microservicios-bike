package pro.ms.billetera.utils.mapper.controller;

import pro.ms.billetera.application.dto.command_usecase.TransaccionCobroCommand;
import pro.ms.billetera.infrastructure.controller.request.TransaccionCobroRequest;

public final class TransaccionCobroRequestMapper {

    private TransaccionCobroRequestMapper() {
    }

    public static TransaccionCobroCommand toTransaccionCobroCommand(TransaccionCobroRequest request) {
        return new TransaccionCobroCommand(
                request.cuentaId(),
                request.monto(),
                request.moneda(),
                request.razon()
        );
    }
}