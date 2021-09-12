package ru.pronichev.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import ru.pronichev.dto.ProductDto;
import ru.pronichev.params.ProductParamList;
import ru.pronichev.servisec.ProductService;

@Controller
@RequiredArgsConstructor
public class ProductControllerImpl implements ProductController {
    private final ProductService productService;

    @Override
    public ResponseEntity<Page<ProductDto>> findAll(
        Long categoryId,
        Long brandId,
        String namePattern,
        Integer page,
        Integer size,
        String sortField
    ) {
        ProductParamList productParamList = ProductParamList.builder()
            .brandId(brandId)
            .categoryId(categoryId)
            .page(page)
            .size(size)
            .sortField(sortField)
            .build();

        return new ResponseEntity<>(productService.findAll(productParamList), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProductDto> getProduct(Long id) {
        return new ResponseEntity<>(productService.getById(id), HttpStatus.OK);
    }
}
