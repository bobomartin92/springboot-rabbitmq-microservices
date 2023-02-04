package dev.decagon.emailservice.consumer;

import dev.decagon.emailservice.dto.OrderEventDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    private final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);

    @RabbitListener(queues = "${rabbitmq.queue.email.name}")
    public void consume(OrderEventDto event){
        LOGGER.info(String.format("Order event received in email service => %s", event.toString()));

        // email service needs to email customer

    }
}
