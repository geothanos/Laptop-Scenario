package gr.knowledge.induction.service.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gr.knowledge.induction.domain.Category;
import gr.knowledge.induction.dto.CategoryDto;

@Component
public class CategoryMapper extends GenericModelMapper<Category, CategoryDto>{
    
    public CategoryMapper(ModelMapper modelMapper){
        super(modelMapper, Category.class, CategoryDto.class);
    }

}

