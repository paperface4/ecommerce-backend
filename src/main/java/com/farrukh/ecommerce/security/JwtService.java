package com.farrukh.ecommerce.security;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Value;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import com.farrukh.ecommerce.user.entity.User;
import java.util.Date;
import io.jsonwebtoken.Jwts;

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
    
}
