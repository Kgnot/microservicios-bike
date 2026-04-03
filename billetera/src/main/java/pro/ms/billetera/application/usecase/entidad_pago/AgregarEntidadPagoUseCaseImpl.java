package pro.ms.billetera.application.usecase.entidad_pago;

import pro.ms.billetera.application.port.in.entidad_pago.AgregarEntidadPagoUseCase;
import pro.ms.billetera.application.port.out.repository.EntidadPagoRepository;
import pro.ms.billetera.domain.model.EntidadPago;

public class AgregarEntidadPagoUseCaseImpl implements AgregarEntidadPagoUseCase {

    private final EntidadPagoRepository entidadPagoRepository;

    public AgregarEntidadPagoUseCaseImpl(EntidadPagoRepository entidadPagoRepository) {
        this.entidadPagoRepository = entidadPagoRepository;
    }

    @Override
    public EntidadPago save(EntidadPago entidadPago) {
        return entidadPagoRepository.save(entidadPago);
    }
}
