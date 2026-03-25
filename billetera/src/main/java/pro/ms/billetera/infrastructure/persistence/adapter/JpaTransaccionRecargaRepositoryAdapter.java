package pro.ms.billetera.infrastructure.persistence.adapter;

import org.springframework.stereotype.Repository;
import pro.ms.billetera.application.port.out.TransaccionRecargaRepository;
import pro.ms.billetera.domain.model.Transaccion;
import pro.ms.billetera.infrastructure.persistence.spring.SpringJpaTransaccionRecargaEntityRepository;

@Repository
public class JpaTransaccionRecargaRepositoryAdapter implements TransaccionRecargaRepository {

    private final SpringJpaTransaccionRecargaEntityRepository repository;

    public JpaTransaccionRecargaRepositoryAdapter(SpringJpaTransaccionRecargaEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    public Transaccion save(Transaccion transaccion) {

        return null;
    }
}
