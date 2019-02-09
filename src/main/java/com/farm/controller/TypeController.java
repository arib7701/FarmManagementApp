package com.farm.controller;

import com.farm.exceptions.ApplicationException;
import com.farm.model.Animal;
import com.farm.model.AnimalType;
import com.farm.model.Delivery;
import com.farm.service.IAnimalTypeService;
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
    private IAnimalTypeService animalTypeService;

    @GetMapping(value="")
    public List<AnimalType> listAllAnimalTypes() {
        return animalTypeService.findAll();
    }

    @GetMapping(value= "/{id}")
    public ResponseEntity<AnimalType> getAnimalTypeById(@PathVariable ("id") int id) {
        AnimalType animalTypeById = animalTypeService.findById(id);
        return new ResponseEntity(animalTypeById, HttpStatus.OK);
    }

    @PostMapping(value="")
    public ResponseEntity<AnimalType> saveNewAnimalType(@RequestBody AnimalType animalTypeBody) throws ApplicationException {
        AnimalType animalTypeSaved = animalTypeService.save(animalTypeBody);
        return new ResponseEntity(animalTypeSaved, HttpStatus.OK);
    }


    @PostMapping(value="/{id}")
    public ResponseEntity<AnimalType> updateAnimalType(@PathVariable("id") int id, @RequestBody AnimalType animalTypeBody) throws ApplicationException{
        AnimalType animalTypeSaved = animalTypeService.update(id, animalTypeBody);
        return new ResponseEntity(animalTypeSaved, HttpStatus.OK);
    }
}
