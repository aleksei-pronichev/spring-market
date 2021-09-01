package ru.pronichev.spec;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductParamList {
    private Long id;

    private Long categoryId;
}
