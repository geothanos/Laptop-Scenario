package gr.knowledge.induction.domain;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users") // Database Table Name
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", length=30, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "first_name", length=30, nullable = false)
    private String firstName;

    @Column(name = "last_name", length=30, nullable = false)
    private String lastName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    // Default Constructor
    public User(){}

    // Constructor
    public User(String username, String password, String firstName, String lastName){
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
