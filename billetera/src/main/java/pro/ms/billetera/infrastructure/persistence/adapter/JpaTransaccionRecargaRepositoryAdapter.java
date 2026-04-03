package pro.ms.billetera.infrastructure.persistence.adapter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import pro.ms.billetera.application.port.out.repository.TransaccionRecargaRepository;
import pro.ms.billetera.domain.model.Transaccion;
import pro.ms.billetera.infrastructure.persistence.jpa.TransaccionRecargaEntity;
import pro.ms.billetera.infrastructure.persistence.spring.SpringJpaTransaccionRecargaEntityRepository;
import pro.ms.billetera.utils.mapper.TransaccionRecargaMapper;

@Repository
@Slf4j
public class JpaTransaccionRecargaRepositoryAdapter implements TransaccionRecargaRepository {

    private final SpringJpaTransaccionRecargaEntityRepository repository;

    public JpaTransaccionRecargaRepositoryAdapter(SpringJpaTransaccionRecargaEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    public Transaccion save(Transaccion transaccion) {
        log.info("Guardando transaccion de recarga:\n {}", transaccion);
        TransaccionRecargaEntity toSave = TransaccionRecargaMapper.toEntity(transaccion);
        log.info("Entidad de transaccion a guardar: \n {}", toSave);
        TransaccionRecargaEntity saved = repository.save(toSave);
        return TransaccionRecargaMapper.toDomain(saved);
    }
}