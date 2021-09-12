package ru.pronichev.servisec;

import java.util.List;
import ru.pronichev.dto.CategoryDto;

public interface CategoryService {
    List<CategoryDto> getAll(String namePattern);
}
