package ru.pronichev.entities.meta;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import ru.pronichev.entities.Category;
import ru.pronichev.entities.Product;

@StaticMetamodel(Category.class)
public class Category_ {
    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    private static volatile SingularAttribute<Category, Long> id;
    private static volatile SingularAttribute<Product, String> title;
    private static volatile SingularAttribute<Product, String> description;
}