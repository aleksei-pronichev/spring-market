package ru.pronichev.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.pronichev.entities.Role;

@Data
@Builder

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RoleDto {

    private Long id;

    private String name;

    public static RoleDto toDto(Role role) {
        return RoleDto.builder()
            .id(role.getId())
            .name(role.getName())
            .build();
    }

    public static RoleDto empty() {
        return new RoleDto();
    }

    public Role toRole() {
        var role = new Role();

        role.setId(id);
        role.setName(name);

        return role;
    }
}
