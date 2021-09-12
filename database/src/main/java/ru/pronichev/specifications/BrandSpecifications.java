package ru.pronichev.specifications;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import ru.pronichev.entities.Brand;
import ru.pronichev.entities.meta.Brand_;

@Component
public class BrandSpecifications {

    public Specification<Brand> withName(String namePattern) {
        return (
            (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(root.get(Brand_.TITLE), namePattern)
        );
    }
}
