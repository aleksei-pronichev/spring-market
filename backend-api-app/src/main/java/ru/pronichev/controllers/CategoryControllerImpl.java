package ru.pronichev.controllers;


import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import ru.pronichev.dto.CategoryDto;
import ru.pronichev.servisec.CategoryService;

@Controller
@RequiredArgsConstructor
public class CategoryControllerImpl implements CategoryController {
    private final CategoryService categoryService;

    @Override
    public ResponseEntity<List<CategoryDto>> findAll(String namePattern) {
        return new ResponseEntity<>(categoryService.getAll(namePattern), HttpStatus.OK);
    }
}
