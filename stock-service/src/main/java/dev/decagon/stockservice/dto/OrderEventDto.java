package dev.decagon.stockservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderEventDto {
    private String status; // pending, progress, completed
    private String message;
    private OrderDto order;
}
