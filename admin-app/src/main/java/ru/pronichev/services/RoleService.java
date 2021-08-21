package ru.pronichev.services;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.pronichev.dto.RoleDto;
import ru.pronichev.entities.Role;
import ru.pronichev.exceptions.NotFoundException;
import ru.pronichev.repositories.RoleRepository;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public List<RoleDto> findAllDto() {
        return roleRepository.findAll()
            .stream()
            .map(RoleDto::toDto)
            .collect(Collectors.toList());
    }

    public Role findById(Long id) {
        return roleRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Role not found"));
    }
}