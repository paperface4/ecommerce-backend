package com.farrukh.ecommerce.security;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Value;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import com.farrukh.ecommerce.user.entity.User;
import java.util.Date;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;

@Service
public class JwtService {
    
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpiration;
    
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(
            jwtSecret.getBytes(StandardCharsets.UTF_8)
    );
}

    public String generateToken(User user){
        Date now = new Date();
        Date expiration = new Date(now.getTime() + jwtExpiration);
         return Jwts.builder()
            .subject(user.getId().toString())
            .claim("role", user.getRole().name())
            .issuedAt(now)
            .expiration(expiration)
            .signWith(getSigningKey())
            .compact();
    }

    private Claims extractAllClaims(String token){
            return Jwts.parser()
            .verifyWith(getSigningKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();

    }
    public Long extractUserId(String token){
          return Long.valueOf(extractAllClaims(token).getSubject());
    }

    public String extractUserRole(String token){
        return extractAllClaims(token).get("role", String.class);
    }

    public boolean isTokenExpired(String token){
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    public boolean isTokenValid(String token, User user){
        final Long userId = extractUserId(token);
        return (userId.equals(user.getId()) && !isTokenExpired(token));
    }

}
