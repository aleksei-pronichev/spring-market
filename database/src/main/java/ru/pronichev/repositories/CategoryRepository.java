package ru.pronichev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pronichev.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
