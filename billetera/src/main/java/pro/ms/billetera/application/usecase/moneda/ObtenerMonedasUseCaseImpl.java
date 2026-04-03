package pro.ms.billetera.application.usecase.moneda;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pro.ms.billetera.application.port.in.moneda.ObtenerMonedasUseCase;
import pro.ms.billetera.application.port.out.repository.MonedaRepository;
import pro.ms.billetera.domain.model.Moneda;

import java.util.List;

@AllArgsConstructor
@Slf4j
public class ObtenerMonedasUseCaseImpl implements ObtenerMonedasUseCase {

    private final MonedaRepository monedaRepository;


    @Override
    public List<Moneda> obtenerMonedas() {
        return monedaRepository.findAll();
    }
}
