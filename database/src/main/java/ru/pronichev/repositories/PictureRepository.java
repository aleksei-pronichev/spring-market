package ru.pronichev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pronichev.entities.Picture;

public interface PictureRepository extends JpaRepository<Picture, Long> {
}
