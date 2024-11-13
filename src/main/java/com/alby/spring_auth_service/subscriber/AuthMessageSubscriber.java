package com.alby.spring_auth_service.subscriber;

import com.alby.spring_auth_service.dto.request.VerifyTokenRequest;
import com.alby.spring_auth_service.service.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthMessageSubscriber {
    private final JwtService jwtService;

    private final ObjectMapper objectMapper;

    private final Jackson2JsonMessageConverter messageConverter;

    @RabbitListener(queues = "authorization.request.queue")
    public Map<String, Object> receiveAuthorizationMessage(Message message) {
        VerifyTokenRequest verifyTokenRequest = objectMapper.convertValue(
                messageConverter.fromMessage(message),
                VerifyTokenRequest.class
        );

        Map<String, Object> response = new HashMap<>();
        response.put("valid", jwtService.validateToken(verifyTokenRequest.getAuthorizationToken()));

        return response;
    }
}
