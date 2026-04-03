package pro.ms.billetera.infrastructure.persistence.spring;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.ms.billetera.infrastructure.persistence.jpa.CuentaEntity;

import java.util.UUID;

public interface SpringJpaCuentaEntityRepository extends JpaRepository<CuentaEntity, UUID> {
}
