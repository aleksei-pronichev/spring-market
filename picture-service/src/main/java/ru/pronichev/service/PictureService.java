package ru.pronichev.service;

import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.pronichev.api.Container;
import ru.pronichev.entities.Picture;
import ru.pronichev.entities.Product;
import ru.pronichev.exception.PictureServiceException;
import ru.pronichev.exceptions.NotFoundException;
import ru.pronichev.repositories.PictureRepository;

@Service
@RequiredArgsConstructor
public class PictureService {

    private final PictureRepository pictureRepository;
    private final Container<String> pictureContainer;

    public String getPictureContentTypeById(Long id) {
        return pictureRepository.findById(id)
            .map(Picture::getContentType)
            .orElseThrow(() -> new NotFoundException("Picture not found"));
    }

    public byte[] getPictureDataById(Long id) {
        var picture = pictureRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Picture not found"));
        if (pictureContainer.contains(picture.getStorageId())) {
            return pictureContainer.getDataById(picture.getStorageId())
                .orElseThrow(() -> new PictureServiceException("Picture data error"));
        } else {
            throw new PictureServiceException("Picture data not found");
        }
    }

    public String createPicture(byte[] pictureData) {
        return pictureContainer.create(pictureData)
            .orElseThrow(() -> new PictureServiceException("Error save file"));
    }

    public void save(Product product, MultipartFile newPicture) {
        try {
            var storagePictureId = createPicture(newPicture.getBytes());

            var picture = new Picture();
            picture.setProduct(product);
            picture.setContentType(newPicture.getContentType());
            picture.setTitle(newPicture.getOriginalFilename());
            picture.setStorageId(storagePictureId);

            pictureRepository.save(picture);
        } catch (IOException e) {
            throw new PictureServiceException(e);
        }
    }

    public void deleteById(Long id) {
        var picture = pictureRepository.getById(id);

        pictureContainer.remove(picture.getStorageId());
        pictureRepository.delete(picture);
    }
}