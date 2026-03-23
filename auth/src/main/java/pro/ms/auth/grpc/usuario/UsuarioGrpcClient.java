package pro.ms.auth.grpc.usuario;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import pro.ms.auth.dto.CreateUserInput;
import pro.ms.auth.event.UserCreatedEvent;
import pro.ms.auth.grpc.usuario.usuario.CreateUserRequest;
import pro.ms.auth.grpc.usuario.usuario.CreateUserResponse;
import pro.ms.auth.grpc.usuario.usuario.UsuarioServiceGrpc;

@Service
public class UsuarioGrpcClient {

    private final UsuarioServiceGrpc.UsuarioServiceBlockingStub stub;
    private final ApplicationEventPublisher publisher;

    public UsuarioGrpcClient(
            @Value("${grpc.usuario.host}") String host,
            @Value("${grpc.usuario.port}") int port,
            ApplicationEventPublisher publisher) {
        this.publisher = publisher;
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(host, port)
                .usePlaintext()
                .build();

        this.stub = UsuarioServiceGrpc.newBlockingStub(channel);
    }

    public String createUser(CreateUserInput input) {
        CreateUserRequest request = CreateUserRequest.newBuilder()
                .setEmail(input.email())
                .setPassword(input.password())
                .setRole(input.role())
                .setNombre(input.nombre() == null ? "" : input.nombre())
                .setNickname(input.nickname() == null ? "" : input.nickname())
                .setTelefono(input.telefono() == null ? "" : input.telefono())
                .setFechaNacimiento(input.fechaNacimiento() == null ? "" : input.fechaNacimiento())
                .build();

        CreateUserResponse response = stub.createUser(request);

        // Publica evento de dominio
        publisher.publishEvent(new UserCreatedEvent(
                input.nombre(),
                input.email(),
                input.role()
        ));

        return response.getUserId();
    }
}
