package pro.ms.auth.controller.response.dto;

public record AuthResponse(
        String accessToken,
        String refreshToken
) {
}
