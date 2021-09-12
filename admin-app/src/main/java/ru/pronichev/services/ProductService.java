package ru.pronichev.services;

import java.util.ArrayList;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.pronichev.dto.ProductDto;
import ru.pronichev.entities.Product;
import ru.pronichev.exceptions.NotFoundException;
import ru.pronichev.repositories.ProductRepository;
import ru.pronichev.service.PictureService;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final BrandService brandService;
    private final PictureService pictureService;

    public Page<Product> findAll(Integer page, Integer size, String sortField) {
        return productRepository.findAll(PageRequest.of(page, size, Sort.by(sortField)));
    }

    public Product findById(Long id) {
        return productRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Product not found"));
    }

    @Transactional
    public void save(ProductDto productDto) {
        var category = categoryService.findById(productDto.getCategory().getId());
        var brand = brandService.findById(productDto.getBrand().getId());

        var product = productDto.toProduct();

        product.setCategory(category);
        product.setBrand(brand);

        savePictures(productDto, product);

        productRepository.save(product);
    }

    private void savePictures(ProductDto productDto, Product product) {
        if (productDto.getNewPictures() == null) {
            return;
        }
        product.setPictures(new ArrayList<>());
        for (var newPicture : productDto.getNewPictures()) {
            pictureService.save(product, newPicture);
        }
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}