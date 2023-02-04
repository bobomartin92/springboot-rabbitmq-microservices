package dev.decagon.orderservice.publisher;

import dev.decagon.orderservice.dto.OrderEventDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderProducer {
    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.order.routing.key}")
    private String orderRoutingKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderProducer.class);

    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(OrderEventDto orderEvent){
        LOGGER.info(String.format("Order event sent to RabbitMQ -> %s", orderEvent.toString()));

        // send an order event to order queue
        rabbitTemplate.convertAndSend(exchange, orderRoutingKey, orderEvent);
    }
}
