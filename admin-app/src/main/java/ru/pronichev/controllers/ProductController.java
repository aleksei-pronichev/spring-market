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
import ru.pronichev.dto.ProductDto;
import ru.pronichev.services.BrandService;
import ru.pronichev.services.CategoryService;
import ru.pronichev.services.ProductService;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final BrandService brandService;

    @Value("categories")
    private String categoriesAttr;

    @Value("brands")
    private String brandsAttr;

    @Value("product_form")
    private String productForm;

    @GetMapping
    public String listPage(@RequestParam("page") Optional<Integer> page,
                           @RequestParam("size") Optional<Integer> size,
                           @RequestParam("sortField") Optional<String> sortField, Model model) {
        model.addAttribute("products",
            productService.findAll(
                page.orElse(1) - 1,
                size.orElse(5),
                sortField.filter(fld -> !fld.isBlank()).orElse("id")
            ).map(ProductDto::toDto)
        );
        return "products";
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("activePage", "Product");
    }

    @GetMapping("/new")
    public String newProductForm(Model model) {
        model.addAttribute("product", ProductDto.empty());
        model.addAttribute(categoriesAttr, categoryService.findAllDto());
        model.addAttribute(brandsAttr, brandService.findAllDto());
        return productForm;
    }

    @GetMapping("/{id}")
    public String editProduct(@PathVariable("id") Long id, Model model) {
        model.addAttribute("product", ProductDto.toDto(productService.findById(id)));
        model.addAttribute(categoriesAttr, categoryService.findAllDto());
        model.addAttribute(brandsAttr, brandService.findAllDto());
        return productForm;
    }

    @PostMapping
    public String update(@Valid @ModelAttribute("product") ProductDto product, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute(categoriesAttr, categoryService.findAllDto());
            model.addAttribute(brandsAttr, brandService.findAllDto());
            return productForm;
        }

        productService.save(product);
        return "redirect:/product";
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productService.deleteById(id);
        return "redirect:/product";
    }
}
