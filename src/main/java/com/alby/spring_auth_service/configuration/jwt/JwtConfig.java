package com.alby.spring_auth_service.configuration.jwt;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;

@Configuration
@Getter
public class JwtConfig {

    @Value("${jwt.key.secret}")
    private String secretKey;

    @Value("${jwt.key.api}")
    private String apiKey;

    @Value("${jwt.expiration}")
    private Long expiration;

    @Bean
    public SecretKey getEncodedSecretKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    @Bean
    public SecretKey getEncodedApiKey() {
        byte[] keyBytes = Decoders.BASE64.decode(apiKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
