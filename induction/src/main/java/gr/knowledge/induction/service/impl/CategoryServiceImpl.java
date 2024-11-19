package gr.knowledge.induction.service.impl;

import gr.knowledge.induction.domain.Category;
import gr.knowledge.induction.dto.CategoryDto;
import gr.knowledge.induction.repository.CategoryRepository;
import gr.knowledge.induction.service.CategoryService;
import gr.knowledge.induction.service.mapper.CategoryMapper;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    // Bean injection
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    // Create new category
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto){
        // Map categoryDto to category (Entity)
        Category categoryEntity = categoryMapper.toEntity(categoryDto);
        // Save category Entity to database
        Category savedCategory = categoryRepository.save(categoryEntity);
        // Map savedCategory Entity to Dto and return it
        return  categoryMapper.toDTO(savedCategory);
    }

    // Retrieve all categories from database
    @Override
    public List<CategoryDto> findAllCategories(){
        // Retrieve from database the list of categories
        List<Category> categories =  categoryRepository.findAllOptional().orElseThrow(
            () -> new RuntimeException("No category found")
        );
        // Map the category list (Entity) to categoryDto list
        List<CategoryDto> categoryDtoList = categoryMapper.toDTO(categories);

        return categoryDtoList;
    }

    // Retrieve single category depending on the laptop_type the user provided
    @Override
    public CategoryDto findCategoriesByLaptopType(String laptopType){
        // Retrieve category (entity) from the database
        Category singleCategory = categoryRepository.findCategory(laptopType).orElseThrow(
            () -> new RuntimeException("Couldn't find category with name: " + laptopType)
        );

        // Map singleCategory (entity) to categoryDto useing categoryMapper
        CategoryDto singleCategoryDto = categoryMapper.toDTO(singleCategory);

        return singleCategoryDto;
    }

    // Update category by id
    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Long Id){
        // Retrieve category (entity) from database
        Category existingCategory = categoryRepository.findById(Id).orElseThrow(
                ()-> new RuntimeException("Couldn't find category with id: " + Id)
        );

        // Set manualy the category id to categoryDto
        categoryDto.setId(Id);
        // Copy contents of categoryDto to existingCategory (Entity)
        BeanUtils.copyProperties(categoryDto, existingCategory);

        // Update: Save existingCategory (Entity) to Database
        categoryRepository.save(existingCategory);

        // Map existingCategory (Entity) to categoryDto
        CategoryDto categoryEntityToDto = categoryMapper.toDTO(existingCategory);

        return categoryEntityToDto;
    }

    // Delete category by id
    @Override
    public void deleteCategoryByLaptopType(String laptopType){
        // Check if category exists
        Category category = categoryRepository.findCategory(laptopType)
                .orElseThrow(()-> new RuntimeException());
        
        categoryRepository.delete(category);
    }
    
}
