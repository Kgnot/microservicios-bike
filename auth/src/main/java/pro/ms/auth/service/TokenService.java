package pro.ms.auth.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pro.ms.auth.domain.AuthResult;

import javax.crypto.SecretKey;
import java.security.Key;
import java.time.Instant;
import java.util.Date;

@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String SECRET;
    @Value("${jwt.expiration}")
    private Long accessExpiration;
    @Value("${jwt.refresh.expiration}")
    private Long refreshExpiration;


    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public AuthResult generate(String subject, String role) {
        String accessToken = buildToken(subject, role, accessExpiration, "access");
        String refreshToken = buildToken(subject, role, refreshExpiration, "refresh");

        Instant expiration = Instant.now().plusMillis(accessExpiration);
        return new AuthResult(subject, accessToken, refreshToken, expiration);
    }

    private String buildToken(String subject, String role, long expiration, String type) {
        return Jwts.builder()
                .subject(subject)
                .claim("role", role)
                .claim("type", type)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getKey())
                .compact();
    }

    public boolean isValid(String token) {
        try {
            Claims claims = parse(token);
            return claims.getExpiration().after(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    // extraccion

    public String getSubject(String token) {
        return parse(token).getSubject();
    }

    public String getRole(String token) {
        return parse(token).get("role", String.class);
    }

    public String getType(String token) {
        return parse(token).get("type", String.class);
    }

    // refrescamos

    public AuthResult refresh(String refreshToken) {

        Claims claims = parse(refreshToken);

        if (!"refresh".equals(claims.get("type"))) {
            throw new RuntimeException("Invalid refresh token");
        }

        if (claims.getExpiration().before(new Date())) {
            throw new RuntimeException("Refresh token expired");
        }

        String subject = claims.getSubject();
        String role = claims.get("role", String.class);

        return generate(subject, role);
    }

    // =========================
    // INTERNAL
    // =========================

    private Claims parse(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
