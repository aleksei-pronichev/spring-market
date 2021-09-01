package ru.pronichev.entities.meta;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import ru.pronichev.entities.Category;
import ru.pronichev.entities.Product;

//@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Product.class)
public class Product_ {
    private static volatile SingularAttribute<Product, Long> id;
    private static volatile SingularAttribute<Product, String> title;
    private static volatile SingularAttribute<Product, String> description;
    private static volatile SingularAttribute<Product, Integer> price;
    private static volatile SingularAttribute<Product, Category> category;

    public static final String ID = "id";
    public static final String CATEGORY = "category";
}
