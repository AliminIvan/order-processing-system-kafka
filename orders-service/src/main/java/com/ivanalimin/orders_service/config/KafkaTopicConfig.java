package com.ivanalimin.orders_service.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic newOrdersTopic() {
        return TopicBuilder.name("new-orders")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic payedOrdersTopic() {
        return TopicBuilder.name("payed-orders")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic sentOrdersTopic() {
        return TopicBuilder.name("sent-orders")
                .partitions(1)
                .replicas(1)
                .build();
    }
}
