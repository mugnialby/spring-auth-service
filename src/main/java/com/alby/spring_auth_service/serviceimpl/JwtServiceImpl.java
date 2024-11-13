package com.alby.spring_auth_service.serviceimpl;

import com.alby.spring_auth_service.configuration.jwt.JwtConfig;
import com.alby.spring_auth_service.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

//    @Value("${server.bcrypt.salt}")
//    @Getter
//    private String salt;

    private final JwtConfig jwtConfig;

    @Override
    public String generateToken(String username) {
        Instant dateNow = Instant.now();
        Instant expiryDate = Instant.now().plus(jwtConfig.getExpiration(), ChronoUnit.HOURS);

        return Jwts.builder()
                .header()
                .add("X-API-KEY", jwtConfig.getEncodedApiKey().getEncoded())
                .and()
                .subject(username)
                .issuedAt(Date.from(dateNow))
                .expiration(Date.from(expiryDate))
                .signWith(jwtConfig.getEncodedSecretKey())
                .compact();
    }

    @Override
    public boolean validateToken(String token) {
        try {
            Jws<Claims> jws = Jwts.parser()
                    .verifyWith(jwtConfig.getEncodedSecretKey())
                    .build()
                    .parseSignedClaims(token);

            if (jws == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Token not found");
            }

            if (jws.getHeader().get("X-API-KEY") == null) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Header is not found");
            }

            if (!jwtConfig.getApiKey().equals(jws.getHeader().get("X-API-KEY"))) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Token is invalid");
            }

            if (jws.getPayload().getExpiration().before(Date.from(Instant.now()))) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Token is expired");
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
