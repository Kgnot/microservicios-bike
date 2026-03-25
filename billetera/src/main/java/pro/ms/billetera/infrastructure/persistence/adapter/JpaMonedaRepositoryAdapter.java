package pro.ms.billetera.infrastructure.persistence.adapter;

import org.springframework.stereotype.Repository;
import pro.ms.billetera.application.port.out.MonedaRepository;
import pro.ms.billetera.domain.model.Moneda;
import pro.ms.billetera.infrastructure.persistence.spring.SpringJpaMonedaEntityRepository;
import pro.ms.billetera.utils.mapper.MonedaMapper;

import java.util.Optional;

@Repository
public class JpaMonedaRepositoryAdapter implements MonedaRepository {

    private final SpringJpaMonedaEntityRepository repository;

    public JpaMonedaRepositoryAdapter(SpringJpaMonedaEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Moneda> findById(String id) {
        return repository.findById(id)
                .map(MonedaMapper::toDomain);
    }

}
