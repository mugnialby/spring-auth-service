package com.alby.spring_auth_service.configuration.rabbitmq;

import lombok.Getter;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.support.RetryTemplate;

@Getter
@Configuration
public class RabbitMQConfiguration {
    @Value("${rabbitmq.queue.authenticate.request}")
    private String rabbitMQQueueAuthenticateRequest;

    @Value("${rabbitmq.queue.authenticate.response}")
    private String rabbitMQQueueAuthenticateResponse;

    @Value("${rabbitmq.exchange.authenticate}")
    private String rabbitMQExchangeAuthenticate;

    @Value("${rabbitmq.queue.authorization.request}")
    private String authorizationRequestQueueName;

    @Value("${rabbitmq.queue.authorization.response}")
    private String authorizationResponseQueueName;

    @Value("${rabbitmq.exchange.authorization}")
    private String authorizationExchangeName;

    @Bean("authenticateRequestQueueBean")
    public Queue authenticateRequestQueue() {
        return new Queue(rabbitMQQueueAuthenticateRequest, false);
    }

    @Bean("authenticateResponseQueueBean")
    public Queue authenticateResponseQueue() {
        return new Queue(rabbitMQQueueAuthenticateResponse, false);
    }

    @Bean("authenticateExchangeBean")
    public DirectExchange authenticateExchange() {
        return new DirectExchange(rabbitMQExchangeAuthenticate);
    }

    @Bean("authorizationRequestQueueBean")
    public Queue authorizationRequestQueue() {
        return new Queue(authorizationRequestQueueName, false);
    }

    @Bean("authorizationResponseQueueBean")
    public Queue authorizationResponeQueue() {
        return new Queue(authorizationResponseQueueName, false);
    }

    @Bean("authorizationExchangeBean")
    public DirectExchange authorizationExchange() {
        return new DirectExchange(authorizationExchangeName);
    }

    @Bean
    public Binding requestBinding(
            @Qualifier("authenticateRequestQueueBean") Queue requestQueue,
            @Qualifier("authenticateExchangeBean") DirectExchange exchange
    ) {
        return BindingBuilder.bind(requestQueue)
                .to(exchange)
                .with(rabbitMQQueueAuthenticateRequest);
    }

    @Bean
    public Binding responseBinding(
            @Qualifier("authenticateResponseQueueBean") Queue responseQueue,
            @Qualifier("authenticateExchangeBean") DirectExchange exchange
    ) {
        return BindingBuilder.bind(responseQueue)
                .to(exchange)
                .with(rabbitMQQueueAuthenticateResponse);
    }

    @Bean
    public Binding authorizationRequestBinding(
            @Qualifier("authorizationRequestQueueBean") Queue requestQueue,
            @Qualifier("authorizationExchangeBean") DirectExchange exchange
    ) {
        return BindingBuilder.bind(requestQueue)
                .to(exchange)
                .with(authorizationRequestQueueName);
    }

    @Bean
    public Binding authorizationResponseBinding(
            @Qualifier("authorizationResponseQueueBean") Queue responseQueue,
            @Qualifier("authorizationExchangeBean") DirectExchange exchange
    ) {
        return BindingBuilder.bind(responseQueue)
                .to(exchange)
                .with(authorizationResponseQueueName);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
