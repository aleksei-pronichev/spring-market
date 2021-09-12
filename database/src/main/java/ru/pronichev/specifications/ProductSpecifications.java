package ru.pronichev.specifications;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import ru.pronichev.entities.Product;
import ru.pronichev.entities.meta.Brand_;
import ru.pronichev.entities.meta.Category_;
import ru.pronichev.entities.meta.Product_;
import ru.pronichev.params.ProductParamList;

@Component
public class ProductSpecifications {

    public Specification<Product> generateSpecification(ProductParamList params) {
        Specification<Product> spec = Specification.where(null);

        if (params.getCategoryId() != null) {
            spec = spec.and(withCategory(params.getCategoryId()));
        }
        if (params.getCategoryId() != null) {
            spec = spec.and(withBrand(params.getBrandId()));
        }

        return spec;
    }

    public Specification<Product> withCategory(Long id) {
        return (
            (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get(Product_.CATEGORY).get(Category_.ID), id)
        );
    }

    public Specification<Product> withBrand(Long id) {
        return (
            (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get(Product_.BRAND).get(Brand_.ID), id)
        );
    }
}
