package ru.pronichev.dto;

import javax.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.pronichev.entities.Category;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoryDto {

    private Long id;

    @NotBlank
    private String title;

    private String description;

    public static CategoryDto toDto(Category category) {
        var dto = new CategoryDto();

        dto.setId(category.getId());
        dto.setTitle(category.getTitle());
        dto.setDescription(category.getDescription());

        return dto;
    }

    public static CategoryDto empty() {
        return new CategoryDto();
    }

    public Category toCategory() {
        var category = new Category();

        category.setId(id);
        category.setTitle(title);
        category.setDescription(description);

        return category;
    }
}
