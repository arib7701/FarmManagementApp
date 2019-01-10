package com.farm.controller;

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
    public ResponseEntity<List<Animal>> getAnimalsByType(@PathVariable ("type") int type) {
        List<Animal> animalsByType = animalService.findByType(type);
        return new ResponseEntity(animalsByType, HttpStatus.OK);
    }

    @GetMapping(value= "/{id}")
    public ResponseEntity<Animal> getAnimalById(@PathVariable ("id") int id) {
        Animal animalById = animalService.findById(id);
        return new ResponseEntity(animalById, HttpStatus.OK);
    }

    @PostMapping(value="")
    public ResponseEntity<Animal> saveNewAnimal( @RequestBody Animal animalBody){
        Animal animalSaved = animalService.save(animalBody);
        return new ResponseEntity(animalSaved, HttpStatus.OK);
    }

}
