package com.agree.chattingapi.conf;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {

    private static String SECRET_KEY_VALUE;

    @Autowired
    public JwtUtils(@Value("${jwt.secret-key}") String secretKeyValue) {
        SECRET_KEY_VALUE = secretKeyValue;
    }
    private static String secretKeyValue;

    private static Key getSecretKey() {
        byte[] secretBytes = secretKeyValue.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(secretBytes);
    }
    private static final long EXPIRATION_TIME = 86400000; // 1 day

    public static String generateToken(String username) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + EXPIRATION_TIME);

        String jwt = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(getSecretKey())
                .compact();

        return jwt;
    }

    public static boolean validateToken(String token, String secretKey) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String extractUsername(String token) {
        Claims claims = Jwts.parser().setSigningKey(getSecretKey()).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}
