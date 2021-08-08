package ru.pronichev.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.pronichev.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByUsernameStartsWith(String prefix);

    @Query("SELECT u " +
        "FROM User u " +
        "WHERE( u.username LIKE CONCAT(:prefix, '%') OR :prefix IS NULL ) " +
        "AND ( u.age >= :minAge OR :minAge IS NULL ) " +
        "AND ( u.age <= :maxAge OR :maxAge IS NULL ) "
    )
    List<User> filterUsers(@Param("prefix") String prefix,
                           @Param("minAge") Integer minAge,
                           @Param("maxAge") Integer maxAge);

    @Query("SELECT DISTINCT u " +
        "FROM User u " +
        "LEFT JOIN FETCH u.roles " +
        "WHERE u.username = :username "
    )
    Optional<User> findByUsername(@Param("username") String username);
}
