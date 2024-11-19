package gr.knowledge.induction.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "categories")
@Setter
@Getter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 30, nullable = false)
    private String laptopType;

    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Laptop> laptops;
}
