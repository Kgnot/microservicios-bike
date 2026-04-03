package pro.ms.billetera.infrastructure.persistence.spring;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.ms.billetera.infrastructure.persistence.jpa.TransaccionRecargaEntity;

import java.util.UUID;

public interface SpringJpaTransaccionRecargaEntityRepository extends JpaRepository<TransaccionRecargaEntity, UUID> {
}
