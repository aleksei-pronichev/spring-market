package ru.pronichev.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.pronichev.entities.Role;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RoleDto {

    private Long id;

    private String name;

    public static RoleDto toDto(Role role) {
        var dto = new RoleDto();

        dto.setId(role.getId());
        dto.setName(role.getName());

        return dto;
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
