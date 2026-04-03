package pro.ms.billetera.infrastructure.persistence.spring;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.ms.billetera.infrastructure.persistence.jpa.TransaccionServicioEntity;

import java.util.UUID;

public interface SpringJpaTransaccionServicioEntityRepository extends JpaRepository<TransaccionServicioEntity, UUID> {
}
