package ru.pronichev.servisec;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.pronichev.dto.CategoryDto;
import ru.pronichev.entities.Category;
import ru.pronichev.repositories.CategoryRepository;
import ru.pronichev.specifications.CategorySpecifications;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategorySpecifications categorySpecifications;

    @Override
    public List<CategoryDto> getAll(String namePattern) {
        Specification<Category> specification = categorySpecifications.withName(namePattern);

        return categoryRepository.findAll(specification)
            .stream()
            .map(CategoryDto::toDto)
            .collect(Collectors.toList());
    }
}
