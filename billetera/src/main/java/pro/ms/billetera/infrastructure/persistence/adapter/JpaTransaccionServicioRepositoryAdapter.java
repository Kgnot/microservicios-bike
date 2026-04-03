package pro.ms.billetera.infrastructure.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pro.ms.billetera.application.port.out.repository.TransaccionServicioRepository;
import pro.ms.billetera.domain.model.Transaccion;
import pro.ms.billetera.infrastructure.persistence.jpa.TransaccionServicioEntity;
import pro.ms.billetera.infrastructure.persistence.spring.SpringJpaTransaccionServicioEntityRepository;
import pro.ms.billetera.utils.mapper.TransaccionServicioMapper;

@Repository
@RequiredArgsConstructor
public class JpaTransaccionServicioRepositoryAdapter implements TransaccionServicioRepository {

    private final SpringJpaTransaccionServicioEntityRepository repository;

    @Override
    public Transaccion save(Transaccion transaccion) {
        TransaccionServicioEntity entity = TransaccionServicioMapper.toEntity(transaccion);
        return null;
    }
}
