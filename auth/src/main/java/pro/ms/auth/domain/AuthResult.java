package pro.ms.auth.domain;

import java.time.Instant;

public record AuthResult(
        String userId,
        String accessToken,
        String refreshToken,
        Instant accessTokenExpiresAt
) {
}
