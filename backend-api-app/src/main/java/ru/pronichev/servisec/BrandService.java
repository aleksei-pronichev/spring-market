package ru.pronichev.servisec;

import java.util.List;
import ru.pronichev.dto.BrandDto;

public interface BrandService {
    List<BrandDto> getAll(String namePattern);
}
