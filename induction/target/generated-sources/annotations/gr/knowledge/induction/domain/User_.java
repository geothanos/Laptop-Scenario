package gr.knowledge.induction.domain;

import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ {

	public static volatile SingularAttribute<User, String> firstName;
	public static volatile SingularAttribute<User, String> lastName;
	public static volatile SingularAttribute<User, String> password;
	public static volatile SetAttribute<User, Role> roles;
	public static volatile SingularAttribute<User, Long> id;
	public static volatile SingularAttribute<User, String> username;

	public static final String FIRST_NAME = "firstName";
	public static final String LAST_NAME = "lastName";
	public static final String PASSWORD = "password";
	public static final String ROLES = "roles";
	public static final String ID = "id";
	public static final String USERNAME = "username";

}

