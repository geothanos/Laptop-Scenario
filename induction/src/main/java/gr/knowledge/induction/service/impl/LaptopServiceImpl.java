package gr.knowledge.induction.service.impl;

import gr.knowledge.induction.domain.Laptop;
import gr.knowledge.induction.dto.LaptopDto;
import gr.knowledge.induction.repository.LaptopRepository;
import gr.knowledge.induction.service.LaptopService;
/* import gr.knowledge.induction.service.mapper.LaptopMapper; */
import gr.knowledge.induction.service.mapper.LaptopMapper;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LaptopServiceImpl implements LaptopService {

    // Inject Beans
    @Autowired
    private LaptopRepository laptopRepository;

    @Autowired
    private LaptopMapper laptopMapper;

    // Create Operation
    @Override
    public LaptopDto createLaptop(LaptopDto laptopDto){
        // Convert laptopDto to entity
        Laptop laptopDtoToEntity = laptopMapper.toEntity(laptopDto);
        // Save entity to database
        Laptop savedLaptop = laptopRepository.save(laptopDtoToEntity);
        // Convert savedLaprop (Entity) to DTO
        LaptopDto savedLaptopDto = laptopMapper.toDTO(savedLaptop);

        return savedLaptopDto;
    }

    // Retrive list of laptops that belong to a specific category (id)
    @Override
    public List<LaptopDto> findLaptopByCategory(Long laptopCategoryId){
        // Retrieve laptop list (of entities) from specific category
        List<Laptop> laptops = laptopRepository.findLaptopByCategory(laptopCategoryId).orElseThrow(
            () -> new RuntimeException("Couldn't find any laptop that belong to category with id: " + laptopCategoryId)
        );
        // Map the laptop list to laptopDto list
        List<LaptopDto> listLaptopDtos = laptopMapper.toDTO(laptops);

        // Return the list of laptopDtos
        return listLaptopDtos;
    }

    // Retrieve laptops of the same brand
    @Override
    public List<LaptopDto> findLaptopByBrand(String lpBrand){
        // Retrieve laptop list (of entities) from specific Brand name
        List<Laptop> laptops = laptopRepository.findLaptopByBrand(lpBrand).orElseThrow(
            () -> new RuntimeException("Couldnt find any " + lpBrand + "Laptops")
        );
        // Map the laptop list to laptopDto list
        List<LaptopDto> listLaptopDtos = laptopMapper.toDTO(laptops);

        return listLaptopDtos;
    }

    // Retrieve laptop by brand and model
    @Override
    public LaptopDto findLaptopByBrandAndModel(String lpBrand, String model){
        // Retrieve laptop list (of entities) with specific brand and model name
        Laptop laptop = laptopRepository.findLaptopByBrandAndModel(lpBrand, model).orElseThrow(
            () -> new RuntimeException("Couldnt find the requested Laptop")
        );
        // Map the laptop to laptopDto
        LaptopDto laptopDto = laptopMapper.toDTO(laptop);

        return laptopDto;
    }

    // Update operation
    @Override
    public LaptopDto updateLaptop(LaptopDto laptopDto, Long Id){
        // Retrieve (Entity) from database the requested laptop 
        Laptop existingLaptop = laptopRepository.findById(Id).orElseThrow(
                ()-> new RuntimeException()
        );
        
        // Set manualy the laptop id to laptopDto
        laptopDto.setId(Id);
        // Copy contents of laptopDto to existingLaptop (Entity)
        BeanUtils.copyProperties(laptopDto, existingLaptop);
        
        // Save laptop Entity to Database
        laptopRepository.save(existingLaptop);

        // Map existingLaptop Entity to Dto
        LaptopDto laptopEntityToDto = laptopMapper.toDTO(existingLaptop);

        return laptopEntityToDto;
    }

    // Delete operation
    @Override
    public void deleteLaptopByModel(String model){
        // Check if laptop exists
        Laptop laptop = laptopRepository.findByModel(model)
                .orElseThrow(()-> new RuntimeException());
        // Delete laptop
        laptopRepository.delete(laptop);
    }

}
