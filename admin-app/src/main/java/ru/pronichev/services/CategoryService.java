package ru.pronichev.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.pronichev.dto.CategoryDto;
import ru.pronichev.repositories.CategoryRepository;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<CategoryDto> findAll() {
        return categoryRepository.findAll().stream()
            .map(CategoryDto::toDto)
            .collect(Collectors.toList());
    }

    public Page<CategoryDto> findAll(Integer page, Integer size, String sortField) {
        return categoryRepository.findAll(PageRequest.of(page, size, Sort.by(sortField)))
            .map(CategoryDto::toDto);
    }

    public Optional<CategoryDto> findById(Long id) {
        return categoryRepository.findById(id)
            .map(CategoryDto::toDto);
    }

    public void save(CategoryDto categoryDto) {
        categoryRepository.save(categoryDto.toCategory());
    }

    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
