package ru.pronichev.services;

import java.io.IOException;
import java.util.ArrayList;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.pronichev.dto.ProductDto;
import ru.pronichev.entities.Picture;
import ru.pronichev.entities.Product;
import ru.pronichev.exceptions.NotFoundException;
import ru.pronichev.exceptions.ProductServiceException;
import ru.pronichev.repositories.PictureRepository;
import ru.pronichev.repositories.ProductRepository;
import ru.pronichev.service.PictureService;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final BrandService brandService;
    private final PictureRepository pictureRepository;
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
        try {
            for (var newPicture : productDto.getNewPictures()) {
                var storagePictureId = pictureService.createPicture(newPicture.getBytes());

                var picture = new Picture();
                picture.setProduct(product);
                picture.setContentType(newPicture.getContentType());
                picture.setTitle(newPicture.getOriginalFilename());
                picture.setStorageId(storagePictureId);

                pictureRepository.save(picture);
            }
        } catch (IOException e) {
            throw new ProductServiceException(e);
        }
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}