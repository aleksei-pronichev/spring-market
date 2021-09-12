package ru.pronichev.dto.converters;


import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.pronichev.dto.RoleDto;

@Component
public class RoleDtoFromStringConverter implements Converter<String, RoleDto> {

    @Override
    public RoleDto convert(String s) {
        var roleDto = RoleDto.empty();
        String[] arr = s.split(";");
        roleDto.setId(Long.parseLong(arr[0]));
        roleDto.setName(arr[1]);

        return roleDto;
    }
}