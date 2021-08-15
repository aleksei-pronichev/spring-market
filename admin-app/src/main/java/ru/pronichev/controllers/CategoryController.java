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
import ru.pronichev.exceptions.NotFoundException;
import ru.pronichev.services.CategoryService;

@Controller
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @Value("category_form")
    private String categoryPage;

    @GetMapping
    public String listPage(@RequestParam("page") Optional<Integer> page,
                           @RequestParam("size") Optional<Integer> size,
                           @RequestParam("sortField") Optional<String> sortField, Model model) {
        model.addAttribute("categories", categoryService.findAll(
            page.orElse(1) - 1,
            size.orElse(5),
            sortField.filter(fld -> !fld.isBlank()).orElse("id")));
        return "categories";
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("activePage", "Category");
    }

    @GetMapping("/new")
    public String newCategoryForm(Model model) {
        model.addAttribute("category", CategoryDto.empty());
        return categoryPage;
    }

    @GetMapping("/{id}")
    public String editCategory(@PathVariable("id") Long id, Model model) {

        model.addAttribute("category", categoryService.findById(id)
            .orElseThrow(() -> new NotFoundException("Category not found")));
        return categoryPage;
    }

    @PostMapping
    public String update(@Valid @ModelAttribute("category") CategoryDto category, BindingResult result) {

        if (result.hasErrors()) {
            return categoryPage;
        }
        categoryService.save(category);
        return "redirect:/category";
    }

    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable("id") Long id) {
        categoryService.deleteById(id);
        return "redirect:/category";
    }
}