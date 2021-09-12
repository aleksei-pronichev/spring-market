package ru.pronichev.specifications;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import ru.pronichev.entities.User;
import ru.pronichev.params.UserListParams;

@Component
public class UserSpecifications {

    @Value("USERNAME")
    private String username;

    @Value("AGE")
    private String age;

    @Value("%")
    private String percent;

    public Specification<User> generateSpecification(UserListParams userListParams) {
        Specification<User> spec = Specification.where(null);

        if (userListParams.getUsernameFilter() != null && !userListParams.getUsernameFilter().isBlank()) {
            spec = spec.and(usernamePrefix(userListParams.getUsernameFilter()));
        }
        if (userListParams.getMinAge() != null) {
            spec = spec.and(minAge(userListParams.getMinAge()));
        }
        if (userListParams.getMaxAge() != null) {
            spec = spec.and(maxAge(userListParams.getMaxAge()));
        }

        return spec;
    }

    private Specification<User> usernamePrefix(String prefix) {
        return (root, query, builder) -> builder.like(root.get(username), prefix + percent);
    }

    private Specification<User> minAge(Integer minAge) {
        return (root, query, builder) -> builder.ge(root.get(age), minAge);
    }

    private Specification<User> maxAge(Integer maxAge) {
        return (root, query, builder) -> builder.le(root.get(age), maxAge);
    }
}