package pro.ms.auth.controller.request;

public record LoginRequest(
        String email,
        String password
) {
}