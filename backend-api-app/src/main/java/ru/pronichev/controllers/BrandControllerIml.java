package ru.pronichev.controllers;


import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import ru.pronichev.dto.BrandDto;
import ru.pronichev.servisec.BrandService;

@Controller
@RequiredArgsConstructor
public class BrandControllerIml implements BrandController {
    private final BrandService brandService;

    @Override
    public ResponseEntity<List<BrandDto>> findAll(String namePattern) {
        return new ResponseEntity<>(brandService.getAll(namePattern), HttpStatus.OK);
    }
}
