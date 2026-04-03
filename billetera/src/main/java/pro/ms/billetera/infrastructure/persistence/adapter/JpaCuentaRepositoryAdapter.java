package pro.ms.billetera.infrastructure.persistence.adapter;

import org.springframework.stereotype.Repository;
import pro.ms.billetera.application.port.out.repository.CuentaRepository;
import pro.ms.billetera.domain.model.Cuenta;
import pro.ms.billetera.infrastructure.persistence.jpa.CuentaEntity;
import pro.ms.billetera.infrastructure.persistence.spring.SpringJpaCuentaEntityRepository;
import pro.ms.billetera.utils.mapper.CuentaMapper;

import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaCuentaRepositoryAdapter implements CuentaRepository {

    private final SpringJpaCuentaEntityRepository repository;

    public JpaCuentaRepositoryAdapter(SpringJpaCuentaEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Cuenta> findById(UUID id) {
        Optional<CuentaEntity> entity = repository.findById(id);
        return entity.map(CuentaMapper::toDomain);
    }

    @Override
    public Cuenta save(Cuenta cuenta) {
        return CuentaMapper.toDomain(repository
                .save(CuentaMapper.toEntity(cuenta)));
    }
}
