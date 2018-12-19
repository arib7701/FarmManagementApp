package com.farm.controller;

import com.farm.model.Animal;
import com.farm.service.IAnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/animal")
public class AnimalController {

    @Autowired
    private IAnimalService animalService;

    @RequestMapping(value="", method = RequestMethod.GET)
    public List<Animal> listAllAnimals() {
        return animalService.findAll();
    }

}
