package pro.ms.auth.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pro.ms.auth.configuration.Result;
import pro.ms.auth.dto.CreateUserInput;
import pro.ms.auth.grpc.usuario.UsuarioGrpcClient;
import pro.ms.auth.usecase.error.RegistryError;

@Component
@RequiredArgsConstructor
public class UseCaseUserRegistry {

    private final UsuarioGrpcClient usuarioGrpcClient;

    public Result<String, RegistryError> register(CreateUserInput input) {
        try {
            String userId = usuarioGrpcClient.createUser(input);
            return new Result.Success<>(userId);
        } catch (Exception e) {
            return new Result.Failure<>(new RegistryError.ExternalServiceError(e.getMessage()));
        }
    }
}