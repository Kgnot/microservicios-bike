package pro.ms.billetera.utils.mapper.controller;

import pro.ms.billetera.application.dto.command_usecase.RecargarCommand;
import pro.ms.billetera.application.dto.command_usecase.TransaccionCobroCommand;
import pro.ms.billetera.application.dto.command_usecase.TransaccionServicioCommand;
import pro.ms.billetera.infrastructure.controller.request.RecargarRequest;
import pro.ms.billetera.infrastructure.controller.request.TransaccionCobroRequest;
import pro.ms.billetera.infrastructure.controller.request.TransaccionServicioRequest;

import java.math.BigDecimal;
import java.util.UUID;

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

    public static TransaccionCobroCommand toTransaccionCobroCommand(TransaccionCobroRequest request) {
        if (request == null) {
            return null;
        }

        return new TransaccionCobroCommand(
                request.cuentaId(),
                request.monto(),
                request.moneda(), // moneda_id solo que estoy pendejo
                request.razon()
        );
    }

    public static TransaccionServicioCommand toTransaccionServicioCommand(TransaccionServicioRequest request) {
        if (request == null) {
            return null;
        }

        return new TransaccionServicioCommand(request.servicioId());
    }
}

