package ru.pronichev.servisec;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.pronichev.dto.ProductDto;
import ru.pronichev.entities.Product;
import ru.pronichev.exceptions.NotFoundException;
import ru.pronichev.params.ProductParamList;
import ru.pronichev.repositories.ProductRepository;
import ru.pronichev.specifications.ProductSpecifications;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductSpecifications productSpecifications;

    @Override
    public Page<ProductDto> findAll(ProductParamList productParamList) {
        Specification<Product> spec = productSpecifications.generateSpecification(productParamList);

        return productRepository.findAll(
            spec,
            PageRequest.of(
                Optional.ofNullable(productParamList.getPage()).orElse(1) - 1,
                Optional.ofNullable(productParamList.getSize()).orElse(5),
                Sort.by(
                    Optional.ofNullable(productParamList.getSortField())
                        .filter(c -> !c.isBlank())
                        .orElse("id")
                )
            )
        ).map(ProductDto::toDto);
    }

    @Override
    public ProductDto getById(Long id) {
        return productRepository.findById(id)
            .map(ProductDto::toDto)
            .orElseThrow(() -> new NotFoundException("Product not found"));
    }
}
