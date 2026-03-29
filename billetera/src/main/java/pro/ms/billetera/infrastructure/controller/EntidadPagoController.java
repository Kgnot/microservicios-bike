package pro.ms.billetera.infrastructure.controller;

import bike.common.api.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.ms.billetera.application.port.in.entidad_pago.AgregarEntidadPagoUseCase;
import pro.ms.billetera.application.port.in.entidad_pago.ObtenerEntidadesPagoUseCase;
import pro.ms.billetera.domain.model.EntidadPago;
import pro.ms.billetera.infrastructure.controller.request.EntidadPagoSaveRequest;
import pro.ms.billetera.infrastructure.controller.response.EntidadPagoResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/entidad-pago")
public class EntidadPagoController {

    private final AgregarEntidadPagoUseCase agregarEntidadPagoUseCase;
    private final ObtenerEntidadesPagoUseCase obtenerEntidadesPagoUseCase;

    public EntidadPagoController(AgregarEntidadPagoUseCase agregarEntidadPagoUseCase,
                                 ObtenerEntidadesPagoUseCase obtenerEntidadesPagoUseCase) {
        this.agregarEntidadPagoUseCase = agregarEntidadPagoUseCase;
        this.obtenerEntidadesPagoUseCase = obtenerEntidadesPagoUseCase;
    }

    /*
     * this method implements save to repository a EntityPage
     * */
    @PostMapping
    public ResponseEntity<ApiResponse<EntidadPagoResponse>> save(@RequestBody EntidadPagoSaveRequest entidadPago) {
        var savedEntidadPago = agregarEntidadPagoUseCase.save(
                new EntidadPago(null, entidadPago.nombre())
        );
        var response = new EntidadPagoResponse(savedEntidadPago.getId(), savedEntidadPago.getNombre());
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<EntidadPagoResponse>>> findAll() {
        var entidadesPago = obtenerEntidadesPagoUseCase.findAll();
        var response = entidadesPago.stream()
                .map(entidad -> new EntidadPagoResponse(entidad.getId(), entidad.getNombre()))
                .toList();
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
