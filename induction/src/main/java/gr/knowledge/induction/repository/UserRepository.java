package gr.knowledge.induction.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
/* import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param; */
import org.springframework.stereotype.Repository;

import gr.knowledge.induction.domain.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    // Custom queries here
    /* @Query("SELECT u FROM User u WHERE u.username =: username")
    Optional<User> findByUsername(@Param("username") String username); */

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);
}
