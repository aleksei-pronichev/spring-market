package ru.pronichev.dto.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.pronichev.dto.CategoryDto;

@Component
public class CategoryDtoFromStringConverter implements Converter<String, CategoryDto> {

    @Override
    public CategoryDto convert(String id) {
        var category = CategoryDto.empty();
        category.setId(Long.parseLong(id));
        return category;
    }
}
