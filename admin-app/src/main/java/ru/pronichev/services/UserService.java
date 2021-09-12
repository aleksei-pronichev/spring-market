package ru.pronichev.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.pronichev.dto.UserDto;
import ru.pronichev.entities.User;
import ru.pronichev.exceptions.NotFoundException;
import ru.pronichev.params.UserListParams;
import ru.pronichev.repositories.UserRepository;
import ru.pronichev.specifications.UserSpecifications;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final UserSpecifications userSpecifications;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Page<User> findWithFilter(UserListParams userListParams) {
        Specification<User> spec = userSpecifications.generateSpecification(userListParams);

        return userRepository.findAll(spec,
            PageRequest.of(
                Optional.ofNullable(userListParams.getPage()).orElse(1) - 1,
                Optional.ofNullable(userListParams.getSize()).orElse(5),
                Sort.by(Optional.ofNullable(userListParams.getSortField())
                    .filter(c -> !c.isBlank())
                    .orElse("id")
                )
            )
        );
    }

    public User findById(Long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("User not found"));
    }

    public void save(UserDto userDto) {
        var roleList = userDto.getRoles()
            .stream()
            .map(roleDto -> roleService.findById(roleDto.getId()))
            .collect(Collectors.toList());
        var user = userDto.toUser();
        user.setRoles(roleList);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        userRepository.save(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
