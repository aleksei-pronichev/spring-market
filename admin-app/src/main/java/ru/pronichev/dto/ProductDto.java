package ru.pronichev.dto;

import javax.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.pronichev.entities.Product;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductDto {

    private Long id;

    @NotBlank
    private String title;

    private String description;

    private Integer price;

    private CategoryDto categoryDto;

    private BrandDto brandDto;

    public static ProductDto toDto(Product product) {
        var dto = new ProductDto();

        dto.setId(product.getId());
        dto.setTitle(product.getTitle());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setCategoryDto(CategoryDto.toDto(product.getCategory()));
        dto.setBrandDto(BrandDto.toDto(product.getBrand()));

        return dto;
    }

    public static ProductDto empty() {
        var productDto = new ProductDto();

        productDto.setCategoryDto(CategoryDto.empty());
        productDto.setBrandDto(BrandDto.empty());

        return productDto;
    }

    public Product toProduct() {
        var product = new Product();

        product.setId(id);
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setCategory(categoryDto.toCategory());
        product.setBrand(brandDto.toBrand());

        return product;
    }
}
