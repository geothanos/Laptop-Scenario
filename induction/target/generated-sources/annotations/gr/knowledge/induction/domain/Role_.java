package gr.knowledge.induction.domain;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Role.class)
public abstract class Role_ {

	public static volatile SingularAttribute<Role, ERole> name;
	public static volatile SingularAttribute<Role, Long> id;

	public static final String NAME = "name";
	public static final String ID = "id";

}

