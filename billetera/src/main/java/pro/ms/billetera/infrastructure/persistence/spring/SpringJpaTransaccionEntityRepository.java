package pro.ms.billetera.infrastructure.persistence.spring;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.ms.billetera.infrastructure.persistence.jpa.TransaccionEntity;

import java.util.UUID;

public interface SpringJpaTransaccionEntityRepository extends JpaRepository<TransaccionEntity, UUID> {
}
