package com.farm.controller;

import com.farm.exceptions.ApplicationException;
import com.farm.model.Animal;
import com.farm.service.IAnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/animals")
public class AnimalController {

    @Autowired
    private IAnimalService animalService;

    @GetMapping( value="")
    public List<Animal> listAllAnimals() {
        return animalService.findAll();
    }

    @GetMapping(value= "/type/{type}")
    public ResponseEntity<List<Animal>> getAllAnimalsByType(@PathVariable ("type") int type) {
        List<Animal> animalsByType = animalService.findByType(type);
        return new ResponseEntity(animalsByType, HttpStatus.OK);
    }

    @GetMapping(value= "/type2/{type}")
    public ResponseEntity<List<Animal>> getAnimalsByType2(@PathVariable ("type") int type) {
        List<Animal> animalsByType = animalService.findByTypeDeadLessThanSixMonths(type);
        return new ResponseEntity(animalsByType, HttpStatus.OK);
    }

    @GetMapping(value= "/{id}")
    public ResponseEntity<Animal> getAnimalById(@PathVariable ("id") int id) {
        Animal animalById = animalService.findById(id);
        return new ResponseEntity(animalById, HttpStatus.OK);
    }

    @PostMapping(value="")
    public ResponseEntity<Animal> saveNewAnimal( @RequestBody Animal animalBody) throws ApplicationException {
        Animal animalSaved = animalService.save(animalBody);
        return new ResponseEntity(animalSaved, HttpStatus.OK);
    }

    @PostMapping(value="/{id}")
    public ResponseEntity<Animal> updateAnimal(@PathVariable ("id") int id, @RequestBody Animal animalBody) throws ApplicationException{
        Animal animalUpdated = animalService.update(id, animalBody);
        return new ResponseEntity(animalUpdated, HttpStatus.OK);
    }


    @DeleteMapping(value="/{id}")
    public ResponseEntity<String> deleteAnimal(@PathVariable ("id") int id) throws ApplicationException{
        boolean deleted = animalService.deleteById(id);
        return new ResponseEntity("Animal deleted successfully", HttpStatus.OK);
    }
}
