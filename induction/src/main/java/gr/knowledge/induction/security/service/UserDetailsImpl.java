package gr.knowledge.induction.security.service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import gr.knowledge.induction.domain.User;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDetailsImpl implements UserDetails{

    private static final long serialVersionUID = 1L;

    private Long id;
    private String username;
    @JsonIgnore
    private String password;
    private String firstName;
    private String lastName;
    private Collection<? extends GrantedAuthority> authorities;

    // Constructor [with user principal including authorities (roles)]
    public UserDetailsImpl(Long id, 
        String username, String password, 
        String firstName, String lastName, 
        Collection<? extends GrantedAuthority> authorities){
            this.id = id;
            this.username = username;
            this.password = password;
            this.firstName = firstName;
            this.lastName = lastName;
            this.authorities = authorities;
        }

    // Method to create a UserDetailsImpl object
    public static UserDetailsImpl build(User user){
        // Authorities contain a list of GrantedAuthority objects
        List<GrantedAuthority> authorities = user.getRoles() // Get a set of roles the user has
            .stream() // Convert the set to a stream for further processing
            .map(roles -> new SimpleGrantedAuthority(roles.getName().name())) // Map each string representaion of user roles to SimpleGrantedAuthority objects
            .collect(Collectors.toList()); // Collect the mapped roles to a list of GrantedAuthority objects

        // Return a new UserDetailsImpl object that has all the user details including the list of authorities that created above
        return new UserDetailsImpl(
            user.getId(), 
            user.getUsername(),
            user.getPassword(),
            user.getFirstName(),
            user.getLastName(),
            authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Long getId(){
        return id;
    }

    @Override
    public String getUsername(){
        return username;
    }

    @Override
    public String getPassword(){
        return password;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    @Override
    public boolean isAccountNonExpired(){
        return true;
    }

    @Override
    public boolean isAccountNonLocked(){
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }

    @Override
    public boolean isEnabled(){
        return true;
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) obj;
        return Objects.equals(id, user.id);
    }
}
