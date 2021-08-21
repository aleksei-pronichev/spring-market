package ru.pronichev.dto;

import java.util.Collection;
import javax.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.pronichev.entities.User;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDto {

    private Long id;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private Integer age;

    private String email;

    @NotBlank
    private String repeatPassword;

    private Collection<RoleDto> roles;

    public static UserDto toDto(User user) {
        var dto = new UserDto();

        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setAge(user.getAge());
        dto.setEmail(user.getEmail());

        return dto;
    }

    public static UserDto empty() {
        return new UserDto();
    }

    public User toUser() {
        var user = new User();

        user.setId(id);
        user.setUsername(username);
        user.setPassword(password);
        user.setAge(age);
        user.setEmail(email);

        return user;
    }
}
