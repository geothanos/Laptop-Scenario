package gr.knowledge.induction.domain;

import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Category.class)
public abstract class Category_ {

	public static volatile SingularAttribute<Category, String> laptopType;
	public static volatile SingularAttribute<Category, Long> id;
	public static volatile ListAttribute<Category, Laptop> laptops;

	public static final String LAPTOP_TYPE = "laptopType";
	public static final String ID = "id";
	public static final String LAPTOPS = "laptops";

}

