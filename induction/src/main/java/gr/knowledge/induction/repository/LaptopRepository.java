package gr.knowledge.induction.repository;

import gr.knowledge.induction.domain.Laptop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LaptopRepository extends JpaRepository<Laptop, Long> {
    // Custom query to retrieve a list of laptops that belong to a specific category
    @Query("SELECT l FROM Laptop l WHERE l.category.id=:laptopCategoryId")
    Optional<List<Laptop>> findLaptopByCategory(@Param("laptopCategoryId") Long laptopCategoryId);

    // Custom query to retrieve a list of laptops that have the same brand name
    @Query("SELECT l FROM Laptop l WHERE l.lpBrand=:lpBrand")
    Optional<List<Laptop>> findLaptopByBrand(@Param("lpBrand") String lpBrand);

    // Custom query to retrieve a laptops with specific Model and Brand name 
    @Query("SELECT l FROM Laptop l WHERE l.lpBrand=:lpBrand AND l.model=:model")
    Optional<Laptop> findLaptopByBrandAndModel(@Param("lpBrand") String lpBrand, @Param("model") String model);

    // Custom query to retrieve a laptop from the model name
    @Query("SELECT l FROM Laptop l WHERE l.model=:model")
    Optional<Laptop> findByModel(@Param("model") String model);
}
