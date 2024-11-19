package gr.knowledge.induction.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "laptop")
@Setter
@Getter
public class Laptop {
    @Id // Specify the primary key of the entity
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Strategy for generating primary key for entity
    //GenerationType.Identity is for auto-increment strategy
    private Long id;

    // "Connection" of field, to database table
    @Column(name = "brand", length=40, nullable = false)
    private String lpBrand;

    @Column(name = "model", length=50, nullable = false)
    private String model;

    @Column(name = "category_id", length=50, nullable = false)
    private Long laptopCategoryId;

    @Column(name = "screen_res", nullable = false)
    private String screenRes;

    @Column(name = "screen_size", nullable = false)
    private Integer screenSize;

    @Column(name = "cpu", length=30, nullable = false)
    private String cpu;

    @Column(name = "gpu", length=30, nullable = false)
    private String gpu;

    @Column(name = "ram", nullable = false)
    private Integer ram;

    @Column(name = "storage", nullable = false)
    private Integer storage;

    // Many to One annotation with JoinColumn
    @ManyToOne
    @JoinColumn(name = "category_id", updatable = false, insertable = false)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Category category;
}
