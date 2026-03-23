package pro.ms.auth.input;

import pro.ms.auth.entity.Credenciales;
import pro.ms.auth.entity.Rol;

import java.util.Objects;
import java.util.UUID;

public record CreateUserInput(
        String email,
        String password,
        Short idRol,
        String nombre,
        String nickname,
        String telefono,
        String fechaNacimiento
) {

    public CreateUserInput {
        email = requireNotBlank(email, "email");
        password = requireNotBlank(password, "password");
        nombre = requireNotBlank(nombre, "nombre");
        nickname = requireNotBlank(nickname, "nickname");
        telefono = requireNotBlank(telefono, "telefono");
        fechaNacimiento = requireNotBlank(fechaNacimiento, "fechaNacimiento");
        Objects.requireNonNull(idRol, "idRol no puede ser null");

        if (password.length() < 8) {
            throw new IllegalArgumentException("password debe tener al menos 8 caracteres");
        }
    }

    private static String requireNotBlank(String value, String field) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(field + " no puede ser null o vacío");
        }
        return value.trim();
    }

    public Credenciales toCredenciales(UUID userId, Rol rol) {
        Credenciales credenciales = new Credenciales();
        credenciales.setId(UUID.randomUUID());
        credenciales.setRol(rol);
        credenciales.setEmail(this.email);
        credenciales.setPasswordHash(this.password); // ideal: hash real en use case/service
        credenciales.setActivo(true);
        credenciales.setUltimoLogin(null);
        credenciales.setUsuarioId(userId);
        return credenciales;
    }

    public Credenciales toCredenciales() {
        Credenciales credenciales = new Credenciales();
        credenciales.setId(UUID.randomUUID());
        credenciales.setEmail(this.email);
        credenciales.setPasswordHash(this.password);
        credenciales.setActivo(true);
        credenciales.setUltimoLogin(null);
        return credenciales;
    }
}
