package ru.pronichev.services;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.pronichev.dto.BrandDto;
import ru.pronichev.entities.Brand;
import ru.pronichev.exceptions.NotFoundException;
import ru.pronichev.repositories.BrandRepository;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;

    public List<Brand> findAll() {
        return brandRepository.findAll();
    }

    public Page<Brand> findAll(Integer page, Integer size, String sortField) {
        return brandRepository.findAll(PageRequest.of(page, size, Sort.by(sortField)));
    }

    public Brand findById(Long id) {
        return brandRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Category not found"));
    }

    public void save(BrandDto brandDto) {
        brandRepository.save(brandDto.toBrand());
    }

    public void deleteById(Long id) {
        brandRepository.deleteById(id);
    }
}
