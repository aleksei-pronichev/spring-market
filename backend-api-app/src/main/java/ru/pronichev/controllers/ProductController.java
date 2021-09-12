package ru.pronichev.controllers;


import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.pronichev.dto.ProductDto;

@RequestMapping("/product")
public interface ProductController {

    @GetMapping("/all")
    ResponseEntity<Page<ProductDto>> findAll(
        @RequestParam("categoryId") Long categoryId,
        @RequestParam("brandId") Long brandId,
        @RequestParam("namePattern") String namePattern,
        @RequestParam("page") Integer page,
        @RequestParam("size") Integer size,
        @RequestParam("sortField") String sortField
    );

    @GetMapping("/{id}")
    ResponseEntity<ProductDto> getProduct(@PathVariable("id") Long id);
}
