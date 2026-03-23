package pro.ms.usuario.grpc;

import io.grpc.stub.StreamObserver;
import org.springframework.grpc.server.service.GrpcService;
import pro.ms.auth.grpc.usuario.usuario.CreateUserRequest;
import pro.ms.auth.grpc.usuario.usuario.CreateUserResponse;
import pro.ms.auth.grpc.usuario.usuario.UsuarioServiceGrpc;
import pro.ms.usuario.entity.Usuario;
import pro.ms.usuario.repository.UsuarioRepository;

import java.time.LocalDate;
import java.util.UUID;

@GrpcService
public class UsuarioGrpcController extends UsuarioServiceGrpc.UsuarioServiceImplBase {

    private final UsuarioRepository usuarioRepository;

    public UsuarioGrpcController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void createUser(CreateUserRequest request,
                           StreamObserver<CreateUserResponse> responseObserver) {

        //Mapear request → entidad
        Usuario usuario = new Usuario();
        usuario.setId(UUID.randomUUID());
        usuario.setNombre(request.getNombre());
        usuario.setNickname(request.getNickname());
        usuario.setTelefono(request.getTelefono());

        // Parse fecha (viene como string)
        usuario.setFechaNacimiento(
                LocalDate.parse(request.getFechaNacimiento())
        );
        // Guardar en DB
        usuarioRepository.save(usuario);

        //Respuesta
        CreateUserResponse response = CreateUserResponse.newBuilder()
                .setUserId(usuario.getId().toString())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}