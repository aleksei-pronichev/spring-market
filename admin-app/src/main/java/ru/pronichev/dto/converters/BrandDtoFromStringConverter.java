package ru.pronichev.dto.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.pronichev.dto.BrandDto;

@Component
public class BrandDtoFromStringConverter implements Converter<String, BrandDto> {

    @Override
    public BrandDto convert(String id) {
        var brand = BrandDto.empty();
        brand.setId(Long.parseLong(id));
        return brand;
    }
}