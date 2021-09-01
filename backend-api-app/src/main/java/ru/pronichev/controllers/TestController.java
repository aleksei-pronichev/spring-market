package ru.pronichev.controllers;

import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.pronichev.dto.CategoryDto;
import ru.pronichev.dto.ProductDto;
import ru.pronichev.servisec.TestService;
import ru.pronichev.spec.ProductParamList;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {
    private final TestService testService;

    @GetMapping()
    public ResponseEntity<Map<CategoryDto, List<ProductDto>>> test(
        @RequestParam(value = "product_id", required = false) Long productId,
        @RequestParam(value = "category_id", required = false) Long categoryId
    ) {
        var param = ProductParamList.builder()
            .id(productId)
            .categoryId(categoryId)
            .build();

        return new ResponseEntity<>(testService.getTestData(param), HttpStatus.OK);
    }
}
