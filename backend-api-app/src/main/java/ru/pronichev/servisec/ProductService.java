package ru.pronichev.servisec;

import org.springframework.data.domain.Page;
import ru.pronichev.dto.ProductDto;
import ru.pronichev.params.ProductParamList;

public interface ProductService {
    Page<ProductDto> findAll(ProductParamList productParamList);

    ProductDto getById(Long id);
}
