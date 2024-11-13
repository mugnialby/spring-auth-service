package com.alby.spring_auth_service.configuration.kafka;

import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@NoArgsConstructor
public class AuthKafkaConfig {

//    @Value("${kafka.topic.auth.events}")
//    private String authEvents;
//
//    @Value("${kafka.topic.user.events}")
//    private String userEvents;
//
//    @Value("${kafka.topic.auth.events.partition}")
//    private int partitionCount;
//
//    @Value("${kafka.producer.id}")
//    private String producerId;
//
//    @Bean
//    public NewTopic authEvents() {
//        return TopicBuilder.name(authEvents)
//                .partitions(partitionCount)
//                .build();
//    }
//
//    public String getCorrelationId() {
//        return new StringBuilder()
//                .append(producerId)
//                .append("_")
//                .append(System.currentTimeMillis())
//                .append("_")
//                .append(UUID.randomUUID())
//                .toString();
//    }
//
//    @Bean
//    public String getAuthEventsTopic() {
//        return this.authEvents;
//    }
//
//    @Bean
//    public String getUserEventsTopic() {
//        return this.userEvents;
//    }
}
