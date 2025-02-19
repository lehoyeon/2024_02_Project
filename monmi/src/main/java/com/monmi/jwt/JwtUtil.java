package com.monmi.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import javax.crypto.SecretKey;

public class JwtUtil {
    private static final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256); // 비밀 키

    public static String generateToken(String monami_user) {
        return Jwts.builder()
                .setSubject(monami_user)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1시간 후 만료
                .signWith(secretKey)
                .compact();
    }

    public static String getUsernameFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    public static boolean isTokenValid(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey) // SECRET_KEY를 사용
                    .build()
                    .parseClaimsJws(token); // 토큰 검증
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false; // 유효하지 않은 토큰
        }
    }
}
