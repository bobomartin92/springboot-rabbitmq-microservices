package dev.decagon.orderservice.controller;

import dev.decagon.orderservice.dto.OrderDto;
import dev.decagon.orderservice.dto.OrderEventDto;
import dev.decagon.orderservice.publisher.OrderProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderProducer orderProducer;

    @PostMapping()
    public ResponseEntity<String> placeOrder(@RequestBody OrderDto order){

        order.setOrderId(UUID.randomUUID().toString());

        OrderEventDto event = new OrderEventDto();
        event.setStatus("PENDING");
        event.setMessage("Order is in pending status");
        event.setOrder(order);

        orderProducer.sendMessage(event);

        return ResponseEntity.ok("Order sent to the RabbitMQ ..");
    }
}
