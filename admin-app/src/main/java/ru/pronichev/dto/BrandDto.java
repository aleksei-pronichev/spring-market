package ru.pronichev.dto;

import javax.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.pronichev.entities.Brand;

@Data

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BrandDto {

    private Long id;

    @NotBlank
    private String title;

    public static BrandDto toDto(Brand brand) {
        var dto = new BrandDto();

        dto.setId(brand.getId());
        dto.setTitle(brand.getTitle());

        return dto;
    }

    public static BrandDto empty() {
        return new BrandDto();
    }

    public Brand toBrand() {
        var brand = new Brand();

        brand.setId(id);
        brand.setTitle(title);

        return brand;
    }
}
