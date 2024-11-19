package gr.knowledge.induction.service;

import gr.knowledge.induction.dto.LaptopDto;

import java.util.List;

public interface LaptopService {
    /** 
     * Create a new laptop
     * 
     * @param laptopDto a Dto of the laptop to be inserted in the database
     * @return LaptopDto object
     */
    LaptopDto createLaptop(LaptopDto laptopDto);

    /** 
     * Return laptop list of a specific category id
     * 
     * @param laptopCategoryId the id of the category
     * @return <code>List</code> of laptops 
     */
    List<LaptopDto> findLaptopByCategory(Long laptopCategoryId);

    /** 
     * Return laptop list of the same brand name
     * 
     * @param lpBrand the name of the Brand
     * @return <code>List</code> of laptops 
     */
    List<LaptopDto> findLaptopByBrand(String lpBrand);

    /** 
     * Return laptop from requested Brand and Model
     * 
     * @param lpBrand the name of laptop's Brand
     * @param model the name of laptop's Model
     * @return laptop, if found
     */
    LaptopDto findLaptopByBrandAndModel(String lpBrand, String model);

    /** 
     * Update a laptop 
     * 
     * @param laptopDto a Dto with the laptop details the user provided from the Request's Body
     * @param Id laptops's id, user want to update
     * @return LaptopDto object of the updated laptop
     */
    LaptopDto updateLaptop(LaptopDto laptopDto, Long Id);

    /** 
     * Delete a laptop
     * 
     * @param model the model name of the laptop to be deleted
     */
    void deleteLaptopByModel(String model);
}
