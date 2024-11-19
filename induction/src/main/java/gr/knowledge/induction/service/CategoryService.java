package gr.knowledge.induction.service;

import gr.knowledge.induction.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    /** 
    * Create a new category
    * 
    * @param categoryDto a Dto of the category to be inserted in the database
    * @return CategoryDto object
    */
    CategoryDto createCategory(CategoryDto categoryDto);

    /** 
     * Return all categories from the database table "categories"
     *  
     * @return <code>List</code> of categories 
     */
    List<CategoryDto> findAllCategories();

    /** 
     * Retrieve single category regarding the laptop_type user requested
     * 
     * @param laptopType the type of the laptop category
     * @return only one category
     */
    CategoryDto findCategoriesByLaptopType(String laptopType);

    /** 
     * Update a category
     * 
     * @param categoryDto the category details the user provided from the Request's Body
     * @param Id category's id, user want to update
     * @return CategoryDto object of the updated category
     */
    CategoryDto updateCategory(CategoryDto categoryDto, Long Id);

    /** 
     * Delete a category
     * 
     * @param laptopType the category name (column: laptop_type) the user want to delete
     */
    void deleteCategoryByLaptopType(String laptopType);
    
}