package pro.ms.billetera.infrastructure.controller;

import bike.common.api.ApiResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.ms.billetera.application.port.in.transaccion.CobrarTransaccionExternoUseCase;
import pro.ms.billetera.application.port.in.transaccion.RecargarSaldoUseCase;
import pro.ms.billetera.infrastructure.controller.request.RecargarRequest;
import pro.ms.billetera.infrastructure.controller.request.TransaccionCobroRequest;
import pro.ms.billetera.utils.mapper.controller.TransaccionCobroRequestMapper;
import pro.ms.billetera.utils.mapper.controller.TransaccionRequestMapper;

@RestController
@RequestMapping("api/v1/transaccion")
public class TransaccionController {

    private final RecargarSaldoUseCase recargarSaldoUseCase;
    private final CobrarTransaccionExternoUseCase cobrarUseCase;


    public TransaccionController(
            @Qualifier("transactionalRecargarSaldoUseCase") RecargarSaldoUseCase recargarSaldoUseCase,
            @Qualifier("transactionalCobrarExternoSaldoUseCase") CobrarTransaccionExternoUseCase cobrarUseCase) {
        this.recargarSaldoUseCase = recargarSaldoUseCase;
        this.cobrarUseCase = cobrarUseCase;
    }

    @PostMapping("/recargar")
    public ResponseEntity<ApiResponse<?>> recargarSaldo(@RequestBody RecargarRequest request) {
        var recargaSaldoCommand = TransaccionRequestMapper.toRecargarCommand(request);
        recargarSaldoUseCase.ejecutar(recargaSaldoCommand);

        return ResponseEntity.ok(ApiResponse.success("Recarga realizada con éxito"));
    }

    @PostMapping("/cobrar")
    public ResponseEntity<ApiResponse<?>> cobrarSaldo(@RequestBody TransaccionCobroRequest request) {
        var cobroCommand = TransaccionCobroRequestMapper.toTransaccionCobroCommand(request);
        cobrarUseCase.ejecutar(cobroCommand);

        return ResponseEntity.ok(ApiResponse.success("Recarga realizada con éxito"));
    }


}
