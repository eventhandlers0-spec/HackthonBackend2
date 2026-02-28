package com.example.EventHandler.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenHelper {
    private static final long JWT_TOKEN_VALIDITY=5*60*60*1000;
    private final SecretKey key= Keys.secretKeyFor(SignatureAlgorithm.HS256);
    public String generateToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+JWT_TOKEN_VALIDITY))
                .signWith(key)
                .compact();
    }
    public String getUsernameFromToken(String token){
        Claims claims=Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
    public boolean validateToken(String token,String username){
        String tokenUsername=getUsernameFromToken(token);
        return tokenUsername.equals(username) && !isTokenExpired(token);
    }
    private boolean isTokenExpired(String token){
        Claims claims=Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getExpiration().before(new Date());
    }
}
