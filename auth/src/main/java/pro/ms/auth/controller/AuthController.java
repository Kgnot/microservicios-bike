package pro.ms.auth.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.ms.auth.configuration.Result;
import pro.ms.auth.controller.request.LoginRequest;
import pro.ms.auth.controller.request.RegisterRequest;
import pro.ms.auth.controller.response.dto.AuthResponse;
import pro.ms.auth.controller.response.generic.ApiResponse;
import pro.ms.auth.input.CreateUserInput;
import pro.ms.auth.usecase.UseCaseLogin;
import pro.ms.auth.usecase.UseCaseUserRegistry;
import pro.ms.auth.usecase.error.RegistryError;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

        private final UseCaseUserRegistry useCaseUserRegistry;
        private final UseCaseLogin useCaseLogin;

        public AuthController(UseCaseUserRegistry useCaseUserRegistry, UseCaseLogin useCaseLogin) {
                this.useCaseUserRegistry = useCaseUserRegistry;
                this.useCaseLogin = useCaseLogin;
        }

        @PostMapping("/login")
        public ResponseEntity<ApiResponse<AuthResponse>> login(
                        @RequestBody LoginRequest request) {

                Result<AuthResponse, String> result = useCaseLogin.login(request.email(), request.password())
                                .map(token -> new AuthResponse(
                                                token.accessToken(),
                                                token.refreshToken()))
                                .mapError(Object::toString);

                return result.isSuccess()
                                ? ResponseEntity.ok(ApiResponse.success(result.unwrap()))
                                : ResponseEntity.badRequest().body(
                                                ApiResponse.error(400, "Login failed", result.unwrapError()));
        }

        @PostMapping("/register")
        public ResponseEntity<ApiResponse<String>> register(
                @Valid @RequestBody RegisterRequest request) {

                CreateUserInput input = new CreateUserInput(
                                request.email(),
                                request.password(),
                                request.idRol(),
                                request.nombre(),
                                request.nickname(),
                                request.telefono(),
                                request.fechaNacimiento());

                Result<String, RegistryError> result = useCaseUserRegistry.register(input);

                return result.isSuccess()
                                ? ResponseEntity.ok(ApiResponse.success(result.unwrap()))
                                : ResponseEntity.badRequest().body(
                                                ApiResponse.error(400, "Registration failed",
                                                                result.unwrapError().getMessage()));
        }
}
