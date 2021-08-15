package ru.pronichev.dto;

import javax.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.pronichev.entities.Product;

@Data
@Builder
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
        return ProductDto.builder()
            .id(product.getId())
            .title(product.getTitle())
            .description(product.getDescription())
            .price(product.getPrice())
            .categoryDto(CategoryDto.toDto(product.getCategory()))
            .brandDto(BrandDto.toDto(product.getBrand()))
            .build();
    }

    public static ProductDto empty() {
        return new ProductDto();
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
