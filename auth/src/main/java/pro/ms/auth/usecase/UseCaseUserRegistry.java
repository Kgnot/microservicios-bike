package pro.ms.auth.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import pro.ms.auth.configuration.Result;
import pro.ms.auth.input.CreateUserInput;
import pro.ms.auth.event.type.UserCreatedEvent;
import pro.ms.auth.grpc.usuario.UsuarioGrpcClient;
import pro.ms.auth.service.credenciales.CredencialesService;
import pro.ms.auth.service.rol.RolService;
import pro.ms.auth.usecase.error.RegistryError;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UseCaseUserRegistry {

    private final UsuarioGrpcClient usuarioGrpcClient;
    private final CredencialesService credencialesService;
    private final RolService rolService;
    private final ApplicationEventPublisher publisher;

    public Result<String, RegistryError> register(CreateUserInput input) {
        try {
            String userId = usuarioGrpcClient.createUser(input);
            var resultRol = rolService.findById(input.idRol());
            if (resultRol.isFailure()) {
                return new Result.Failure<>(new RegistryError.RolNotFoundError(input.idRol()));
            }
            credencialesService.save(input.toCredenciales(
                    UUID.fromString(userId),
                    resultRol.unwrap())
            );

            // publicamos:
            publisher.publishEvent(new UserCreatedEvent(
                    input.nombre(),
                    input.email(),
                    input.idRol()
            ));

            return new Result.Success<>(userId);
        } catch (Exception e) {
            return new Result.Failure<>(new RegistryError.ExternalServiceError(e.getMessage()));
        }
    }
}