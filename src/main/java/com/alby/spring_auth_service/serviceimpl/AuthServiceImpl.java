package com.alby.spring_auth_service.serviceimpl;

import com.alby.spring_auth_service.configuration.rabbitmq.RabbitMQConfiguration;
import com.alby.spring_auth_service.dto.request.LoginRequest;
import com.alby.spring_auth_service.dto.request.VerifyTokenRequest;
import com.alby.spring_auth_service.dto.response.LoginResponse;
import com.alby.spring_auth_service.dto.response.UserResponse;
import com.alby.spring_auth_service.dto.response.WebResponse;
import com.alby.spring_auth_service.producer.RabbitMQAuthRequestProducer;
import com.alby.spring_auth_service.service.AuthService;
import com.alby.spring_auth_service.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final JwtService jwtService;

    private final RabbitMQAuthRequestProducer authRequestProducer;

    private final RabbitMQConfiguration rabbitMQConfiguration;

    @Override
    public WebResponse<LoginResponse> login(LoginRequest request) {
        Map<String, Object> message = new HashMap<>();
        message.put("username", request.getUsername());
        message.put("password", request.getPassword());

        UserResponse userResponse = authRequestProducer.sendAuthenticationRequest(
                rabbitMQConfiguration.getRabbitMQQueueAuthenticateRequest(),
                message
        );

        if (userResponse == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        LoginResponse loginResponse = LoginResponse.builder()
                .token(jwtService.generateToken(userResponse.getUsername()))
                .build();

        return WebResponse.<LoginResponse>builder()
                .message("OK")
                .data(loginResponse)
                .build();
    }

    @Override
    public boolean verify(VerifyTokenRequest request) {
        return jwtService.validateToken(request.getAuthorizationToken());
    }
}