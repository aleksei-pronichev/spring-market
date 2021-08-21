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
import ru.pronichev.dto.CategoryDto;
import ru.pronichev.services.CategoryService;

@Controller
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @Value("category_form")
    private String categoryForm;

    @GetMapping
    public String listPage(@RequestParam("page") Optional<Integer> page,
                           @RequestParam("size") Optional<Integer> size,
                           @RequestParam("sortField") Optional<String> sortField, Model model) {
        model.addAttribute("categories",
            categoryService.findAll(
                page.orElse(1) - 1,
                size.orElse(5),
                sortField.filter(fld -> !fld.isBlank()).orElse("id")
            ).map(CategoryDto::toDto)
        );
        return "categories";
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("activePage", "Category");
    }

    @GetMapping("/new")
    public String newCategoryForm(Model model) {
        model.addAttribute("category", CategoryDto.empty());
        return categoryForm;
    }

    @GetMapping("/{id}")
    public String editCategory(@PathVariable("id") Long id, Model model) {

        model.addAttribute("category", CategoryDto.toDto(categoryService.findById(id)));

        return categoryForm;
    }

    @PostMapping
    public String update(@Valid @ModelAttribute("category") CategoryDto categoryDto, BindingResult result) {

        if (result.hasErrors()) {
            return categoryForm;
        }
        categoryService.save(categoryDto);

        return "redirect:/category";
    }

    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable("id") Long id) {
        categoryService.deleteById(id);
        return "redirect:/category";
    }
}