package ru.pronichev.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.pronichev.entities.Picture;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PictureDto {

    private Long id;

    private String title;

    private String contentType;

    private String storageId;

    public static PictureDto toDto(Picture picture) {
        var dto = new PictureDto();

        dto.setId(picture.getId());
        dto.setTitle(picture.getTitle());
        dto.setContentType(picture.getContentType());
        dto.setStorageId(picture.getStorageId());

        return dto;
    }

    public static PictureDto empty() {
        return new PictureDto();
    }

    public Picture toPicture() {
        var picture = new Picture();

        picture.setId(id);
        picture.setTitle(title);
        picture.setStorageId(storageId);
        picture.setContentType(contentType);

        return picture;
    }
}