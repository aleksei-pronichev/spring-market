package ru.pronichev.controllers;


import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.pronichev.dto.CategoryDto;

@RequestMapping("/category")
public interface CategoryController {

    @GetMapping("/all")
    ResponseEntity<List<CategoryDto>> findAll(
        @RequestParam("namePattern") String namePattern
    );
}
