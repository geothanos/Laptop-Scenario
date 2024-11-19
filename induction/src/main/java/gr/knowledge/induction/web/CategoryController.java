package gr.knowledge.induction.web;

import gr.knowledge.induction.dto.CategoryDto;
import gr.knowledge.induction.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    // CREATE
    @PostMapping(value = "/create")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
        CategoryDto createdCategoryDto = categoryService.createCategory(categoryDto);
        return new ResponseEntity<CategoryDto>(createdCategoryDto, HttpStatus.CREATED);
    }

    // SELECT
    @GetMapping(value = "/readAll")
    public ResponseEntity<List<CategoryDto>> findAllCategories(){
        List<CategoryDto> categoriesDtoList = categoryService.findAllCategories();
        return new ResponseEntity<List<CategoryDto>>(categoriesDtoList, HttpStatus.OK);
    }

    // SELECT by laptopType
    @GetMapping(value = "/readByTypeName/{laptopType}")
    public ResponseEntity<CategoryDto> findCategoriesByLaptopType(@PathVariable("laptopType") String laptopType){
        CategoryDto singlCategoryDto = categoryService.findCategoriesByLaptopType(laptopType);
        return new ResponseEntity<CategoryDto>(singlCategoryDto, HttpStatus.FOUND);
    }

    // UPDATE
    @PutMapping(value = "/updateById/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto, @PathVariable("id") String id){
        CategoryDto categoryUpdateDto = categoryService.updateCategory(categoryDto, Long.parseLong(id));
        return new ResponseEntity<CategoryDto>(categoryUpdateDto, HttpStatus.OK);
    }

    // DELETE
    @DeleteMapping(value = "/deleteByTypeName/{laptopType}")
    public ResponseEntity<CategoryDto> deleteCategoryByLaptopType(@PathVariable("laptopType") String laptopType) {
        categoryService.deleteCategoryByLaptopType(laptopType);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
