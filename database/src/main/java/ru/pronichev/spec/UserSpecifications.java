package ru.pronichev.spec;

import org.springframework.data.jpa.domain.Specification;
import ru.pronichev.entities.User;

public final class UserSpecifications {

    public static Specification<User> usernamePrefix(String prefix) {
        return (root, query, builder) -> builder.like(root.get("USERNAME"), prefix + "%");
    }

    public static Specification<User> minAge(Integer minAge) {
        return (root, query, builder) -> builder.ge(root.get("AGE"), minAge);
    }

    public static Specification<User> maxAge(Integer maxAge) {
        return (root, query, builder) -> builder.le(root.get("AGE"), maxAge);
    }
}