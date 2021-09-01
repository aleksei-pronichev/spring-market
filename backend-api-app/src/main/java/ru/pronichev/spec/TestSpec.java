package ru.pronichev.spec;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import ru.pronichev.entities.Product;
import ru.pronichev.entities.meta.Category_;
import ru.pronichev.entities.meta.Product_;

@Component
public class TestSpec {

    public Specification<Product> generateSpecification(ProductParamList params) {
        Specification<Product> spec = Specification.where(null);

        if (params.getId() != null) {
            spec = spec.and(withProductId(params.getId()));
        }
        if (params.getCategoryId() != null) {
            spec = spec.and(withCategoryId(params.getCategoryId()));
        }

        return spec;
    }

    private Specification<Product> withProductId(Long id) {
        return (
            (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get(Product_.ID), id)
        );
    }

    private Specification<Product> withCategoryId(Long id) {
        return (
            (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get(Product_.CATEGORY).get(Category_.ID), id)
        );
    }
}
