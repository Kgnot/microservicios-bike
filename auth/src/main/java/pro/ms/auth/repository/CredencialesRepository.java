package pro.ms.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.ms.auth.entity.Credenciales;

import java.util.Optional;
import java.util.UUID;

public interface CredencialesRepository extends JpaRepository<Credenciales, UUID> {
    Optional<Credenciales> findByEmail(String email);
}
