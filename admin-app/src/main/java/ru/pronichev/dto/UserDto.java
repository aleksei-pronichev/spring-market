package ru.pronichev.dto;

import java.util.Collection;
import javax.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.pronichev.entities.User;

@Data
@Builder
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
        return UserDto.builder()
            .id(user.getId())
            .username(user.getUsername())
            .password(user.getPassword())
            .age(user.getAge())
            .email(user.getEmail())
            .build();
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
