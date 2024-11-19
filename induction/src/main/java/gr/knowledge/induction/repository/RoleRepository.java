package gr.knowledge.induction.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gr.knowledge.induction.domain.ERole;
import gr.knowledge.induction.domain.Role;
//import java.util.List;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
    // Custom queries can be written here
    //@Query("")
    //Optional<List<Role>> findByRoleName(ERole name);

    Optional<Role> findByName(ERole name);
}
