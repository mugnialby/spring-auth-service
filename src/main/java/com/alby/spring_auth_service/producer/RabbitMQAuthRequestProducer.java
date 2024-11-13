package com.alby.spring_auth_service.producer;

import com.alby.spring_auth_service.dto.response.UserResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class RabbitMQAuthRequestProducer {
    private final RabbitTemplate rabbitTemplate;

    private final ObjectMapper objectMapper;

    public UserResponse sendAuthenticationRequest(
            String routingKey,
            Map<String, Object> messageRequest
    ) {
        rabbitTemplate.setReplyTimeout(1000);
        Map<String, Object> response = (Map<String, Object>) rabbitTemplate.convertSendAndReceive(
                routingKey,
                messageRequest
        );

        return objectMapper.convertValue(response, UserResponse.class);
    }
}
