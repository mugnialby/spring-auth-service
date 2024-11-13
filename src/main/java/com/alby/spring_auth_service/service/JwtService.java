package com.alby.spring_auth_service.service;

public interface JwtService {

    String generateToken(String username);

    boolean validateToken(String token);
}
