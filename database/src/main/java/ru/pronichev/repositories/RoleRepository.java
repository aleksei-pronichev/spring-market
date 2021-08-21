package ru.pronichev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pronichev.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
