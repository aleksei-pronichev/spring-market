package ru.pronichev.controllers;

import java.util.Optional;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.pronichev.dto.BrandDto;
import ru.pronichev.dto.CategoryDto;
import ru.pronichev.services.BrandService;
import ru.pronichev.services.CategoryService;

@Controller
@RequestMapping("/brand")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @Value("brand_form")
    private String brandForm;

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("activePage", "Brand");
    }

    @GetMapping
    public String listPage(@RequestParam("page") Optional<Integer> page,
                           @RequestParam("size") Optional<Integer> size,
                           @RequestParam("sortField") Optional<String> sortField, Model model) {
        model.addAttribute("brands",
            brandService.findAll(
                page.orElse(1) - 1,
                size.orElse(5),
                sortField.filter(fld -> !fld.isBlank()).orElse("id")
            ).map(BrandDto::toDto)
        );
        return "brands";
    }

    @GetMapping("/new")
    public String newCategoryForm(Model model) {
        model.addAttribute("brand", BrandDto.empty());
        return brandForm;
    }

    @GetMapping("/{id}")
    public String editCategory(@PathVariable("id") Long id, Model model) {

        model.addAttribute("brand", BrandDto.toDto(brandService.findById(id)));

        return brandForm;
    }

    @PostMapping
    public String update(@Valid @ModelAttribute("brand") BrandDto brandDto, BindingResult result) {

        if (result.hasErrors()) {
            return brandForm;
        }
        brandService.save(brandDto);

        return "redirect:/brand";
    }

    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable("id") Long id) {
        brandService.deleteById(id);
        return "redirect:/brand";
    }
}