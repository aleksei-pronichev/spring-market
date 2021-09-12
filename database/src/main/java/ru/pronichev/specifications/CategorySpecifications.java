package ru.pronichev.specifications;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import ru.pronichev.entities.Category;
import ru.pronichev.entities.meta.Category_;

@Component
public class CategorySpecifications {

    public Specification<Category> withName(String namePattern) {
        return (
            (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(root.get(Category_.TITLE), namePattern)
        );
    }
}
