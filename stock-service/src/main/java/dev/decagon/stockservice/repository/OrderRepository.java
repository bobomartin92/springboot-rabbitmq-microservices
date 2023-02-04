package dev.decagon.stockservice.repository;

import dev.decagon.stockservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
