package ru.pronichev.params;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductParamList {
    private Long categoryId;

    private Long brandId;

    private Integer page;

    private Integer size;

    private String sortField;
}
