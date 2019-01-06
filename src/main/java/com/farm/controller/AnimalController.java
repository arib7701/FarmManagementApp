package com.farm.controller;

import com.farm.model.Animal;
import com.farm.service.IAnimalService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @RequestMapping(value="", method = RequestMethod.GET)
    public List<Animal> listAllAnimals() {
        return animalService.findAll();
    }

    @RequestMapping(value= "/{type}", method = RequestMethod.GET)
    public ResponseEntity<List<Animal>> getAnimalsByType(@PathVariable ("type") String type) {
        List<Animal> animalsByType = animalService.findByType(type);
        return new ResponseEntity(animalsByType, HttpStatus.OK);
    }

}
