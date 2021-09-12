package ru.pronichev.entities.meta;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import ru.pronichev.entities.Brand;
import ru.pronichev.entities.Category;
import ru.pronichev.entities.Product;

@StaticMetamodel(Product.class)
public class Product_ {
    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String PRICE = "price";
    public static final String CATEGORY = "category";
    public static final String BRAND = "brand";
    private static volatile SingularAttribute<Product, Long> id;
    private static volatile SingularAttribute<Product, String> title;
    private static volatile SingularAttribute<Product, String> description;
    private static volatile SingularAttribute<Product, Integer> price;
    private static volatile SingularAttribute<Product, Category> category;
    private static volatile SingularAttribute<Product, Brand> brand;
}
