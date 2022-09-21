package com.tamiresntt.consumer.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

   @Bean
    DirectExchange deadLetterExchange() {
        return new DirectExchange("deadLetterExchange");
   }

   @Bean
    DirectExchange exchange() {
        return new DirectExchange("javainuseExchange");
   }

   @Bean
   Queue dlq() {
        return QueueBuilder.durable("deadLetter.queue").build();
   }

   @Bean
    Queue queue

}