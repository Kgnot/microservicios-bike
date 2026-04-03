package pro.ms.billetera.infrastructure.persistence.spring;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.ms.billetera.infrastructure.persistence.jpa.TransaccionCobroEntity;

import java.util.UUID;

public interface SpringJpaTransaccionCobroRepository extends JpaRepository<TransaccionCobroEntity, UUID> {
}
