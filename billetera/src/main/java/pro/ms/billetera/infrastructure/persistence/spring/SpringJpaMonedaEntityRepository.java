package pro.ms.billetera.infrastructure.persistence.spring;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.ms.billetera.infrastructure.persistence.jpa.MonedaEntity;

public interface SpringJpaMonedaEntityRepository extends JpaRepository<MonedaEntity, String> {
}
