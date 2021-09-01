package ru.pronichev.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.pronichev.entities.Product;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductDto {

    private Long id;

    private String title;

    private String description;

    private Integer price;

    public static ProductDto toDto(Product product) {
        var dto = new ProductDto();

        dto.setId(product.getId());
        dto.setTitle(product.getTitle());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());

        return dto;
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

        return product;
    }
}
