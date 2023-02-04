package dev.decagon.stockservice.consumer;

import dev.decagon.stockservice.dto.OrderDto;
import dev.decagon.stockservice.dto.OrderEventDto;
import dev.decagon.stockservice.entity.Order;
import dev.decagon.stockservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderConsumer {

    private Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);

    private final OrderRepository orderRepository;

    @RabbitListener(queues = "${rabbitmq.queue.order.name}")
    public void consume(OrderEventDto event){
        LOGGER.info(String.format("Order event received => %s", event.toString()));

        // save order event data in database
        OrderDto orderDto = event.getOrder();
        Order order = new Order();
        order.setOrderId(orderDto.getOrderId());
        order.setName(orderDto.getName());
        order.setQty(orderDto.getQty());
        order.setPrice(orderDto.getPrice());
        order.setOrderStatus(event.getStatus());

        orderRepository.save(order);
    }
}
