package ru.pronichev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pronichev.entities.Order;

public interface OrdeRepository extends JpaRepository<Order, Long> {
}
