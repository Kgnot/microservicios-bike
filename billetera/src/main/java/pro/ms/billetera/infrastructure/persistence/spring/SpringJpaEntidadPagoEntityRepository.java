package pro.ms.billetera.infrastructure.persistence.spring;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.ms.billetera.infrastructure.persistence.jpa.EntidadPagoEntity;

public interface SpringJpaEntidadPagoEntityRepository extends JpaRepository<EntidadPagoEntity, Short> {
}
