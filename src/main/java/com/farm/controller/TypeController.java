package com.farm.controller;

import com.farm.model.AnimalType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/types")
public class TypeController {

    @Autowired
    private I animalService;

    @RequestMapping(value="", method = RequestMethod.GET)
    public List<AnimalType> listAllAnimals() {
        return animalService.findAll();
    }

    @RequestMapping(value= "/{type}", method = RequestMethod.GET)
    public ResponseEntity<List<AnimalType>> getAnimalsByType(@PathVariable("type") String type) {
        List<AnimalType> animalsByType = animalService.findByType(type);
        return new ResponseEntity(animalsByType, HttpStatus.OK);
    }

    @RequestMapping(value= "/{id}", method = RequestMethod.GET)
    public ResponseEntity<AnimalType> getAnimalById(@PathVariable ("id") int id) {
        AnimalType animalById = animalService.findById(id);
        return new ResponseEntity(animalById, HttpStatus.OK);
    }
}
