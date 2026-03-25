package pro.ms.billetera.infrastructure.persistence.adapter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import pro.ms.billetera.application.port.out.TransaccionRepository;
import pro.ms.billetera.domain.model.Transaccion;
import pro.ms.billetera.infrastructure.persistence.jpa.TransaccionEntity;
import pro.ms.billetera.infrastructure.persistence.spring.SpringJpaTransaccionEntityRepository;
import pro.ms.billetera.utils.mapper.TransaccionMapper;

import java.util.Optional;
import java.util.UUID;

@Repository
@Slf4j
public class JpaTransaccionRepositoryAdapter implements TransaccionRepository {

    private final SpringJpaTransaccionEntityRepository repository;

    public JpaTransaccionRepositoryAdapter(SpringJpaTransaccionEntityRepository repository) {
        this.repository = repository;
    }


    @Override
    public Optional<Transaccion> findById(UUID id) {
        log.info("Se va a buscar transaccion por id:{}", id);
        return repository.findById(id)
                .map(e -> TransaccionMapper.toDomain(e, null, null));
    }

    @Override
    public Transaccion save(Transaccion transaccion) {

        TransaccionEntity toSave = TransaccionMapper.toEntity(transaccion);
        TransaccionEntity saved = repository.save(toSave);
        return TransaccionMapper.toDomain(saved, null, null);
    }
}
