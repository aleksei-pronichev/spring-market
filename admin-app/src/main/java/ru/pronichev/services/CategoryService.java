package ru.pronichev.services;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.pronichev.dto.CategoryDto;
import ru.pronichev.entities.Category;
import ru.pronichev.exceptions.NotFoundException;
import ru.pronichev.repositories.CategoryRepository;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public List<CategoryDto> findAllDto() {
        return categoryRepository.findAll().stream()
            .map(CategoryDto::toDto)
            .collect(Collectors.toList());
    }

    public Page<Category> findAll(Integer page, Integer size, String sortField) {
        return categoryRepository.findAll(PageRequest.of(page, size, Sort.by(sortField)));
    }

    public Category findById(Long id) {
        return categoryRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Category not found"));
    }

    public void save(CategoryDto categoryDto) {
        categoryRepository.save(categoryDto.toCategory());
    }

    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
