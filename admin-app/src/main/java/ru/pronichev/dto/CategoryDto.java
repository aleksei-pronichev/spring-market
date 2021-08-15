package ru.pronichev.dto;

import javax.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.pronichev.entities.Category;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoryDto {

    private Long id;

    @NotBlank
    private String title;

    private String description;

    public static CategoryDto toDto(Category category) {
        return CategoryDto.builder()
            .id(category.getId())
            .title(category.getTitle())
            .build();
    }

    public static CategoryDto empty() {
        return new CategoryDto();
    }

    public Category toCategory() {
        var category = new Category();

        category.setTitle(title);
        category.setId(id);
        category.setDescription(description);

        return category;
    }
}
