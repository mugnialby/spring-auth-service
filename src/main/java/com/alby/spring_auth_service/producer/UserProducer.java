package com.alby.spring_auth_service.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserProducer {

//    private final KafkaTemplate<String, String> kafkaTemplate;
//
//    private final AuthKafkaConfig authKafkaConfig;
//
//    public void produce(String jsonData, String messageKey) {
//        Message<String> message = MessageBuilder
//                .withPayload(jsonData)
//                .setHeader(KafkaHeaders.TOPIC, authKafkaConfig.getUserEventsTopic())
//                .setHeader(KafkaHeaders.CORRELATION_ID, authKafkaConfig.getCorrelationId())
//                .setHeader(KafkaHeaders.KEY, messageKey)
//                .build();
//
//        log.info(String.format("Message sent %s", message));
//        kafkaTemplate.send(message);
//    }
}
