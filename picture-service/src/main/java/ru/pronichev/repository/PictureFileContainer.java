package ru.pronichev.repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import ru.pronichev.api.Container;

@Repository
public class PictureFileContainer implements Container<String> {

    @Value("${pictures.storage.path}")
    private String storagePath;

    @Override
    public boolean contains(String id) {
        return Files.exists(Paths.get(storagePath, id));
    }

    @Override
    public Optional<byte[]> getDataById(String id) {
        try {
            return Optional.of(Files.readAllBytes(Paths.get(storagePath, id)));
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<String> create(byte[] data) {
        String pictureID = UUID.randomUUID().toString();

        try (var outputStream = Files.newOutputStream(Paths.get(storagePath, pictureID))) {
            outputStream.write(data);
            return Optional.of(pictureID);
        } catch (IOException ex) {
            return Optional.empty();
        }
    }
}