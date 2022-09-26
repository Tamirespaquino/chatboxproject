package com.tamiresntt.consumer.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String DEAD_LETTER_QUEUE_EXCHANGE = "user.exchange.dlq";
    public static final String QUEUE_EXCHANGE = "user.exchange";
    public static final String DEAD_LETTER_QUEUE = "user.queue.dlq";
    public static final String QUEUE = "user.queue";
    public static final String QUEUE_ROUTING_KEY = "user.queue";
    public static final String DEAD_LETTER_QUEUE_ROUTING_KEY = "user.queue.dlq";
    @Autowired
    private ConnectionFactory cachingConnectionFactory;


    @Bean
    DirectExchange deadLetterExchange() {
        return new DirectExchange(DEAD_LETTER_QUEUE_EXCHANGE);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(QUEUE_EXCHANGE);
    }

    @Bean
    Queue dlqQueue() {
        return QueueBuilder.durable(DEAD_LETTER_QUEUE).build();
    }

    @Bean
    public Queue queue() {
        return QueueBuilder.durable(QUEUE)
                .withArgument("x-dead-letter-exchange", DEAD_LETTER_QUEUE_EXCHANGE)
                .withArgument("x-dead-letter-routing-key", DEAD_LETTER_QUEUE_ROUTING_KEY)
                .build();
    }

    @Bean
    Binding dlqBinding() {
        return BindingBuilder.bind(dlqQueue()).to(deadLetterExchange()).with(DEAD_LETTER_QUEUE_ROUTING_KEY);
    }

    @Bean
    Binding binding() {
        return BindingBuilder.bind(queue()).to(exchange()).with(QUEUE_ROUTING_KEY);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}