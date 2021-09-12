package ru.pronichev.dto;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.pronichev.entities.Picture;
import ru.pronichev.entities.Product;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductDto {

    private Long id;
    private String title;
    private String description;
    private Integer price;
    private CategoryDto category;
    private BrandDto brand;
    private List<Long> pictures;

    public static ProductDto toDto(Product product) {
        var dto = new ProductDto();

        dto.setId(product.getId());
        dto.setTitle(product.getTitle());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setCategory(CategoryDto.toDto(product.getCategory()));
        dto.setBrand(BrandDto.toDto(product.getBrand()));
        dto.setPictures(
            product.getPictures()
                .stream()
                .map(Picture::getId)
                .collect(Collectors.toList())
        );

        return dto;
    }

    public static ProductDto empty() {
        var dto = new ProductDto();

        dto.setCategory(CategoryDto.empty());
        dto.setBrand(BrandDto.empty());

        return new ProductDto();
    }

    public Product toProduct() {
        var product = new Product();

        product.setId(id);
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);

        return product;
    }
}
