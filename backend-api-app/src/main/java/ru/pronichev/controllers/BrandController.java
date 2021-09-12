package ru.pronichev.controllers;


import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.pronichev.dto.BrandDto;

@RequestMapping("/brand")
public interface BrandController {

    @GetMapping("/all")
    ResponseEntity<List<BrandDto>> findAll(
        @RequestParam("namePattern") String namePattern
    );
}
