package gr.knowledge.induction.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import gr.knowledge.induction.service.mapper.CategoryMapper;
import gr.knowledge.induction.service.mapper.LaptopMapper;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }

    @Bean
    public LaptopMapper laptopClassMapper(ModelMapper modelMapper){
        return new LaptopMapper(modelMapper);
    }

    @Bean
    public CategoryMapper CategoryClassMapper(ModelMapper modelMapper){
        return new CategoryMapper(modelMapper);
    }
    
}
