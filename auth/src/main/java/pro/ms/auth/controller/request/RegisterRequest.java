package pro.ms.auth.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @NotBlank String email,
        @NotBlank @Size(min = 8) String password,
        @NotBlank String nombre,
        @NotBlank String nickname,
        @NotBlank String telefono,
        @NotBlank String fechaNacimiento,
        @NotNull Short idRol
) {
}