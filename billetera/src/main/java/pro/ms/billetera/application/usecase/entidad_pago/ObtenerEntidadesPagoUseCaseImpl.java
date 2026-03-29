package pro.ms.billetera.application.usecase.entidad_pago;

import pro.ms.billetera.application.port.in.entidad_pago.ObtenerEntidadesPagoUseCase;
import pro.ms.billetera.application.port.out.repository.EntidadPagoRepository;
import pro.ms.billetera.domain.model.EntidadPago;

import java.util.List;

public class ObtenerEntidadesPagoUseCaseImpl implements ObtenerEntidadesPagoUseCase {

    private final EntidadPagoRepository entidadPagoRepository;

    public ObtenerEntidadesPagoUseCaseImpl(EntidadPagoRepository entidadPagoRepository) {
        this.entidadPagoRepository = entidadPagoRepository;
    }

    @Override
    public List<EntidadPago> findAll() {
        return entidadPagoRepository.findAll();
    }
}
