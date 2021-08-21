package ru.pronichev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pronichev.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
