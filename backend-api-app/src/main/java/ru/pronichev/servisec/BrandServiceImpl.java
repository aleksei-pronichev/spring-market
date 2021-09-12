package ru.pronichev.servisec;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.pronichev.dto.BrandDto;
import ru.pronichev.entities.Brand;
import ru.pronichev.repositories.BrandRepository;
import ru.pronichev.specifications.BrandSpecifications;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final BrandSpecifications brandSpecifications;

    @Override
    public List<BrandDto> getAll(String namePattern) {
        Specification<Brand> specification = brandSpecifications.withName(namePattern);

        return brandRepository.findAll(specification)
            .stream()
            .map(BrandDto::toDto)
            .collect(Collectors.toList());
    }
}
