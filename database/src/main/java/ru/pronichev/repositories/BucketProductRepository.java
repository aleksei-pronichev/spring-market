package ru.pronichev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pronichev.entities.BucketProduct;

public interface BucketProductRepository extends JpaRepository<BucketProduct, Long> {
}
