package pro.ms.auth.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pro.ms.auth.configuration.Result;
import pro.ms.auth.domain.AuthResult;
import pro.ms.auth.entity.Credenciales;
import pro.ms.auth.repository.CredencialesRepository;
import pro.ms.auth.service.TokenService;
import pro.ms.auth.usecase.error.LoginError;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UseCaseLogin {

    private final CredencialesRepository credencialesRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public Result<AuthResult, LoginError> login(String email, String password) {
        Optional<Credenciales> credenciales = credencialesRepository.findByEmail(email);
        if (credenciales.isEmpty()) {
            return new Result.Failure<>(new LoginError.UserNotFound(email));
        }
        var credencial = credenciales.get();
        if (!credencial.getActivo()) {
            return new Result.Failure<>(new LoginError.UserInactive(email));
        }
        if (!passwordEncoder.matches(password, credencial.getPasswordHash())) {
            return new Result.Failure<>(new LoginError.InvalidPassword(email));
        }
        return new Result.Success<>(tokenService.generate(
                credencial.getEmail(),
                credencial.getRol().getNombre()
        ));
    }

}
