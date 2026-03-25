package pro.ms.billetera.infrastructure.persistence.adapter;

import org.springframework.stereotype.Repository;
import pro.ms.billetera.application.port.out.repository.EntidadPagoRepository;
import pro.ms.billetera.domain.model.EntidadPago;
import pro.ms.billetera.infrastructure.persistence.jpa.EntidadPagoEntity;
import pro.ms.billetera.infrastructure.persistence.spring.SpringJpaEntidadPagoEntityRepository;
import pro.ms.billetera.utils.mapper.EntidadPagoMapper;

import java.util.Optional;

@Repository
public class JpaEntidadPagoRepositoryAdapter implements EntidadPagoRepository {

    private final SpringJpaEntidadPagoEntityRepository repository;

    public JpaEntidadPagoRepositoryAdapter(SpringJpaEntidadPagoEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<EntidadPago> findById(Short id) {
        Optional<EntidadPagoEntity> find = repository.findById(id);
        return find.map(EntidadPagoMapper::toDomain);
    }
}
