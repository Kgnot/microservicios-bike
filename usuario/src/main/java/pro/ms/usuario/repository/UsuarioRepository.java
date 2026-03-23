package pro.ms.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.ms.usuario.entity.Usuario;

import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
}
