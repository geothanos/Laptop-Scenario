package gr.knowledge.induction.service.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gr.knowledge.induction.domain.Laptop;
import gr.knowledge.induction.dto.LaptopDto;

@Component
public class LaptopMapper extends GenericModelMapper<Laptop, LaptopDto>{
    
    public LaptopMapper(ModelMapper modelMapper){
        super(modelMapper, Laptop.class, LaptopDto.class);
    }
    
}
