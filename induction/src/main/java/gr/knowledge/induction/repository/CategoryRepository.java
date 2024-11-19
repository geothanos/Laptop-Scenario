package gr.knowledge.induction.repository;

import gr.knowledge.induction.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    // Custom query to retrieve all categories
    @Query("SELECT c FROM Category c")
    Optional<List<Category>> findAllOptional();

    // Custom query to retrieve a categaty of a specific name
    @Query("SELECT c FROM Category c WHERE c.laptopType=:laptopType")
    Optional<Category> findCategory(@Param("laptopType") String laptopType);

}
