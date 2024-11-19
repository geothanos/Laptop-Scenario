package gr.knowledge.induction.web;

import gr.knowledge.induction.dto.LaptopDto;
import gr.knowledge.induction.service.LaptopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/laptop")
public class LaptopController {

    @Autowired
    LaptopService laptopService;

    // CREATE
    @PostMapping(value = "/create")
    public ResponseEntity<LaptopDto> createLaptop(@RequestBody LaptopDto laptopDto){
        LaptopDto savedLaptop = laptopService.createLaptop(laptopDto);
        return new ResponseEntity<LaptopDto>(savedLaptop, HttpStatus.CREATED);
    }

    // SELECT
    @GetMapping(value = "/readByCategory/{laptopCategoryId}")
    public ResponseEntity<?> findLaptopByCategory(@PathVariable("laptopCategoryId") Long laptopCategoryId){
        List<LaptopDto> laptopDtoList = laptopService.findLaptopByCategory(laptopCategoryId);
        return ResponseEntity.ok(laptopDtoList);
    }

    // SELECT
    @GetMapping(value = "/readByBrand/{lpBrand}")
    public ResponseEntity<?> findLaptopByBrand(@PathVariable("lpBrand") String lpBrand){
        List<LaptopDto> laptopDtoList = laptopService.findLaptopByBrand(lpBrand);
        return ResponseEntity.ok(laptopDtoList);
    }

    // SELECT
    @GetMapping(value = "/readByBrandAndModel/brand/{lpBrand}/model/{model}")
    public ResponseEntity<?> findLaptopByBrandAndModel(@PathVariable("lpBrand") String lpBrand, @PathVariable("model") String model){
        LaptopDto laptopDto = laptopService.findLaptopByBrandAndModel(lpBrand, model);
        return ResponseEntity.ok(laptopDto);
    }

    // UPDATE
    @PutMapping(value = "/updateById/{id}")
    public ResponseEntity<LaptopDto> updateLaptop(@RequestBody LaptopDto laptopDto, @PathVariable("id") Long id){
        LaptopDto updatedLaptopDto = laptopService.updateLaptop(laptopDto, id);
        return new ResponseEntity<LaptopDto>(updatedLaptopDto, HttpStatus.OK);
    }

    // DELETE
    @DeleteMapping(value = "/deleteByModel/{model}")
    public ResponseEntity<LaptopDto> deleteLaptop(@PathVariable("model") String model) {
        laptopService.deleteLaptopByModel(model);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}