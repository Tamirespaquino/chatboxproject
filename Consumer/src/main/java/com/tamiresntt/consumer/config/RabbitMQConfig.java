package com.tamiresntt.consumer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMQConfig {

    public static final String QUEUE = "user.queue";
    public static final String ROUTING_KEY = "user.queue";
    public static final String EXCHANGE = "user.exchange";
    public static final String DEAD_LETTER_QUEUE = "user.queue.dlq";
    public static final String DEAD_LETTER_QUEUE_EXCHANGE = "user.exchange.dlq";
    public static final String DEAD_LETTER_QUEUE_ROUTING_KEY = "user.queue.dlq";

    @Autowired
    private ConnectionFactory cachingConnectionFactory;

    @Bean
    public Queue queue() {
        return new Queue(QUEUE, false);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(EXCHANGE);
    }

    @Bean
    Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }
    @Bean
    public Queue dlqQueue() {
        return new Queue(DEAD_LETTER_QUEUE, false);
    }

    @Bean
    DirectExchange dlqExchange() {
        return new DirectExchange(DEAD_LETTER_QUEUE_EXCHANGE);
    }

    @Bean
    Binding dlqBinding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(DEAD_LETTER_QUEUE_ROUTING_KEY);
    }
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(cachingConnectionFactory);
        factory.setMessageConverter(jsonMessageConverter());

        return factory;
    }

    @Bean
    public Queue outgoingQueue() {
        Map<String, Object> args = new HashMap<String, Object>();

        // default exchange - where the message will be republished
        args.put("user.exchange", "");

        // route to the incoming queue when the TTL occurs. The dlq queue name
        args.put("user.queue.dlq", DEAD_LETTER_QUEUE);

        // TTL 5 seconds. Defines how long the message will stay on the queue
        // before its Time-To-Live expires and it's placed on the incoming queue
        args.put("x-message-ttl", 5000);

        return new Queue(QUEUE, false, false, false, args);
    }

    @Bean
    public RabbitTemplate outgoingSender() {

        RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory);
        rabbitTemplate.setDefaultReceiveQueue(outgoingQueue().getName());
        rabbitTemplate.setRoutingKey(outgoingQueue().getName());
        rabbitTemplate.setMessageConverter(jsonMessageConverter());

        return rabbitTemplate;
    }

    @Bean
    public Queue incomingQueue() {
        return new Queue(DEAD_LETTER_QUEUE);
    }

}