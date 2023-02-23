package com.helloworld.hello_world.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

@Component
public class TokenProvider {
    private final String secret;
    private final int expiration;

    public TokenProvider(@Value("${jwt.secret}") String secret, @Value("${jwt.expiration}") int expiration) {
        this.secret = secret;
        this.expiration = expiration;
    }

    public String createToken(String userEmail) {

        return Jwts.builder()
                .setSubject(userEmail)
                .setIssuedAt(new Date())
                .setExpiration(Date.from(Instant.now().plusSeconds(expiration)))
                .signWith(
                        SignatureAlgorithm.HS256,
                        TextCodec.BASE64.decode(secret)
                )
                .compact();
    }

    public String getUserEmail(String token) {
        Claims claims = Jwts.parser().setSigningKey(TextCodec.BASE64.decode(secret))
                .parseClaimsJws(token).getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(TextCodec.BASE64.decode(secret))
                    .parseClaimsJws(authToken);
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }
}
