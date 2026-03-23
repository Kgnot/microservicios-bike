package pro.ms.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.ms.auth.configuration.Result;
import pro.ms.auth.controller.request.LoginRequest;
import pro.ms.auth.controller.response.dto.AuthResponse;
import pro.ms.auth.controller.response.generic.ApiResponse;
import pro.ms.auth.usecase.UseCaseLogin;
import pro.ms.auth.usecase.UseCaseUserRegistry;

@RestController
@RequestMapping("api/v1/login")
public class AuthController {

    private final UseCaseUserRegistry useCaseUserRegistry;
    private final UseCaseLogin useCaseLogin;

    public AuthController(UseCaseUserRegistry useCaseUserRegistry, UseCaseLogin useCaseLogin) {
        this.useCaseUserRegistry = useCaseUserRegistry;
        this.useCaseLogin = useCaseLogin;
    }


    @PostMapping
    public ResponseEntity<ApiResponse<AuthResponse>> login(
            @RequestBody LoginRequest request) {

        Result<AuthResponse, String> result =
                useCaseLogin.login(request.email(), request.password())
                        .map(token -> new AuthResponse(
                                token.accessToken(),
                                token.refreshToken()
                        ))
                        .mapError(Object::toString);

        return result.isSuccess()
                ? ResponseEntity.ok(ApiResponse.success(result.unwrap()))
                : ResponseEntity.badRequest().body(
                ApiResponse.error(400, "Login failed", result.unwrapError())
        );
    }


}
