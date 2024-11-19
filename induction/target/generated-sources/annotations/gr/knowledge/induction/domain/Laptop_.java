package gr.knowledge.induction.domain;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Laptop.class)
public abstract class Laptop_ {

	public static volatile SingularAttribute<Laptop, String> screenRes;
	public static volatile SingularAttribute<Laptop, Long> laptopCategoryId;
	public static volatile SingularAttribute<Laptop, Integer> screenSize;
	public static volatile SingularAttribute<Laptop, String> lpBrand;
	public static volatile SingularAttribute<Laptop, String> cpu;
	public static volatile SingularAttribute<Laptop, String> model;
	public static volatile SingularAttribute<Laptop, Long> id;
	public static volatile SingularAttribute<Laptop, Integer> storage;
	public static volatile SingularAttribute<Laptop, Category> category;
	public static volatile SingularAttribute<Laptop, String> gpu;
	public static volatile SingularAttribute<Laptop, Integer> ram;

	public static final String SCREEN_RES = "screenRes";
	public static final String LAPTOP_CATEGORY_ID = "laptopCategoryId";
	public static final String SCREEN_SIZE = "screenSize";
	public static final String LP_BRAND = "lpBrand";
	public static final String CPU = "cpu";
	public static final String MODEL = "model";
	public static final String ID = "id";
	public static final String STORAGE = "storage";
	public static final String CATEGORY = "category";
	public static final String GPU = "gpu";
	public static final String RAM = "ram";

}

