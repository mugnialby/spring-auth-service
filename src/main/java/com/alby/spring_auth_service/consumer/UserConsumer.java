package com.alby.spring_auth_service.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class UserConsumer {

//    @KafkaListener(
//            topics = "user.events"
//    )
//    public void loginRequestConsumer(
//            @Payload String message,
//            @Header(KafkaHeaders.CORRELATION_ID) String receivedCorrelationId,
//            CompletableFuture<String> completableFuture,
//            String senderCorrelationId
//    ) {
//        if (senderCorrelationId.equals(receivedCorrelationId)) {
//            log.info(String.format("Event Type received -> %s | Message received -> %s",
//                    receivedCorrelationId, message));
//
//            completableFuture.complete(message);
//        }
//    }
}