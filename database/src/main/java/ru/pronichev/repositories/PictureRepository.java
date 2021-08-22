package ru.pronichev.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.pronichev.entities.Picture;
import ru.pronichev.entities.Product;

public interface PictureRepository extends JpaRepository<Picture, Long> {
    List<Picture> findAllByProduct(Product product);
}
