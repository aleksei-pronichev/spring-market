package ru.pronichev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pronichev.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
