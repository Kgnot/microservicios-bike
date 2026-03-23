package pro.ms.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.ms.auth.entity.Rol;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Short> {

    Optional<Rol> findByNombre(String nombre);
}
