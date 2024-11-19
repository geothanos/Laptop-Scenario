package gr.knowledge.induction.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LaptopDto {
    
    private Long id;
    private String lpBrand;
    private String model;
    private Long laptopCategoryId;
    private String screenRes;
    private Integer screenSize;
    private String cpu;
    private String gpu;
    private Integer ram;
    private Integer storage;
   
}
