package ru.pronichev.servisec;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.pronichev.dto.CategoryDto;
import ru.pronichev.dto.ProductDto;
import ru.pronichev.entities.Product;
import ru.pronichev.repositories.ProductRepository;
import ru.pronichev.spec.ProductParamList;
import ru.pronichev.spec.TestSpec;

@Service
@RequiredArgsConstructor
public class TestService {
    private final ProductRepository productRepository;
    private final TestSpec spec;

    public Map<CategoryDto, List<ProductDto>> getTestData(ProductParamList param) {
        var specification = spec.generateSpecification(param);

        var products = productRepository.findAll(specification);

        return convert(products);
    }

    private Map<CategoryDto, List<ProductDto>> convert(List<Product> products) {
        Map<CategoryDto, List<ProductDto>> map = new HashMap<>();
        products.stream()
            .collect(Collectors.groupingBy(Product::getCategory))
            .forEach((key, value) -> map.put(CategoryDto.toDto(key), value.stream().map(ProductDto::toDto).collect(Collectors.toList())));
        return map;
    }
}
