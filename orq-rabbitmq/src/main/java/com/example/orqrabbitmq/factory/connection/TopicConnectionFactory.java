package com.example.orqrabbitmq.factory.connection;

import com.example.orqrabbitmq.utils.RoutingTopology;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicConnectionFactory {

    @Bean
    public RabbitAdmin topicRabbitAdmin(CachingConnectionFactory connectionFactory) {

        RabbitAdmin admin = new RabbitAdmin(connectionFactory);
        TopicExchange exchange = new TopicExchange(RoutingTopology.TOPIC.getExchange());

        admin.declareExchange(exchange);

        Queue eventsQueue = new Queue(RoutingTopology.TOPIC.getQueue()[0]);
        Binding eventsBinding = BindingBuilder.bind(eventsQueue).to(exchange).with("events.*");

        admin.declareQueue(eventsQueue);
        admin.declareBinding(eventsBinding);

        Queue securityQueue = new Queue(RoutingTopology.TOPIC.getQueue()[1]);
        Binding securityBinding = BindingBuilder.bind(securityQueue).to(exchange).with("security.*");

        admin.declareQueue(securityQueue);
        admin.declareBinding(securityBinding);

        Queue weatherQueue = new Queue(RoutingTopology.TOPIC.getQueue()[2]);
        Binding weatherBinding = BindingBuilder.bind(weatherQueue).to(exchange).with("weather.*");

        admin.declareQueue(weatherQueue);
        admin.declareBinding(weatherBinding);

        return admin;
    }
}
