package ru.pronichev.dto.params;


import lombok.Data;

@Data
public class UserListParams {

    private String usernameFilter;

    private Integer minAge;

    private Integer maxAge;

    private Integer page;

    private Integer size;

    private String sortField;
}
