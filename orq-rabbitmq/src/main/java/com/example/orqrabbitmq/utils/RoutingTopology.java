package com.example.orqrabbitmq.utils;

import lombok.Getter;

@Getter
public enum RoutingTopology {
    
    TOPIC("rabbitmq.topic.exchange", "rabbitmq.topic.queue.events", "rabbitmq.topic.queue.security", "rabbitmq.topic.queue.weather");

    private final String exchange;
    private final String[] queue;

    RoutingTopology(String exchange, String ... queues) {
        this.exchange = exchange;
        this.queue = queues;
    }
}
