package ru.pronichev.entities.meta;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import ru.pronichev.entities.Category;

@StaticMetamodel(Category.class)
public class Category_ {
    private static volatile SingularAttribute<Category, Long> id;

    public static final String ID = "id";
}
