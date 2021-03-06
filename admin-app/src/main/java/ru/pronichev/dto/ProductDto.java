package ru.pronichev.dto;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import ru.pronichev.entities.Product;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductDto {

    private Long id;

    @NotBlank
    private String title;

    private String description;

    private Integer price;

    private CategoryDto category;

    private BrandDto brand;

    private List<PictureDto> pictures;

    private MultipartFile[] newPictures;

    public static ProductDto toDto(Product product) {
        var dto = new ProductDto();

        dto.setId(product.getId());
        dto.setTitle(product.getTitle());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setCategory(CategoryDto.toDto(product.getCategory()));
        dto.setBrand(BrandDto.toDto(product.getBrand()));
        dto.setPictures(
            product.getPictures().stream()
                .map(PictureDto::toDto)
                .collect(Collectors.toList())
        );

        return dto;
    }

    public static ProductDto empty() {
        var productDto = new ProductDto();

        productDto.setCategory(CategoryDto.empty());
        productDto.setBrand(BrandDto.empty());

        return productDto;
    }

    public Product toProduct() {
        var product = new Product();

        product.setId(id);
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setCategory(category.toCategory());
        product.setBrand(brand.toBrand());

        return product;
    }
}
