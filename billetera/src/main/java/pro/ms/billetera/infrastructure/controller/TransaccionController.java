package pro.ms.billetera.infrastructure.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.ms.billetera.application.port.in.RecargarSaldoUseCase;

@RestController
@RequestMapping("api/v1/transaccion")
public class TransaccionController {

    private final RecargarSaldoUseCase recargarSaldoUseCase;


    public TransaccionController(RecargarSaldoUseCase recargarSaldoUseCase) {
        this.recargarSaldoUseCase = recargarSaldoUseCase;
    }

    @PostMapping("/recargar")
    public void recargarSaldo() {

    }

}
