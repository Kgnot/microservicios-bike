package pro.ms.billetera.infrastructure.controller;

import bike.common.api.ApiResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.ms.billetera.application.port.in.transaccion.RecargarSaldoUseCase;
import pro.ms.billetera.infrastructure.controller.request.RecargarRequest;
import pro.ms.billetera.utils.mapper.controller.TransaccionRequestMapper;

@RestController
@RequestMapping("api/v1/transaccion")
public class TransaccionController {

    private final RecargarSaldoUseCase recargarSaldoUseCase;


    public TransaccionController(
            @Qualifier("transactionalRecargarSaldoUseCase") RecargarSaldoUseCase recargarSaldoUseCase) {
        this.recargarSaldoUseCase = recargarSaldoUseCase;
    }

    @PostMapping("/recargar")
    public ResponseEntity<ApiResponse<?>> recargarSaldo(@RequestBody RecargarRequest request) {
        var recargaSaldoCommand = TransaccionRequestMapper.toRecargarCommand(request);
        recargarSaldoUseCase.ejecutar(recargaSaldoCommand);

        return ResponseEntity.ok(ApiResponse.success("Recarga realizada con éxito"));
    }

}
