package pro.ms.auth.dto;

public record CreateUserInput(
        String email,
        String password,
        String role,
        String nombre,
        String nickname,
        String telefono,
        String fechaNacimiento
) {
}
