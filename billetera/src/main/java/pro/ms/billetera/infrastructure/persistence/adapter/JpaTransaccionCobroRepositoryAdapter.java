package pro.ms.billetera.infrastructure.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pro.ms.billetera.application.port.out.repository.TransaccionCobroRepository;
import pro.ms.billetera.domain.model.Transaccion;
import pro.ms.billetera.infrastructure.persistence.jpa.TransaccionCobroEntity;
import pro.ms.billetera.infrastructure.persistence.spring.SpringJpaTransaccionCobroRepository;
import pro.ms.billetera.utils.mapper.TransaccionCobroMapper;

@Repository
@RequiredArgsConstructor
public class JpaTransaccionCobroRepositoryAdapter implements TransaccionCobroRepository {

    private final SpringJpaTransaccionCobroRepository repository;


    @Override
    public Transaccion save(Transaccion transaccion) {
        TransaccionCobroEntity toSave = TransaccionCobroMapper.toEntity(transaccion);
        TransaccionCobroEntity entityT = repository.save(toSave);
        // aquí podríamos manejar un try para el tema del repositorio
        return TransaccionCobroMapper.toDomain(entityT);
    }
}
