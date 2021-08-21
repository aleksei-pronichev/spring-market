package ru.pronichev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pronichev.entities.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> {
}
