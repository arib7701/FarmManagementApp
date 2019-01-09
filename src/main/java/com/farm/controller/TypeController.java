package com.farm.controller;

import com.farm.model.AnimalType;
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
}
