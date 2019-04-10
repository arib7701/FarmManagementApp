package com.farm.controller;

import com.farm.exceptions.ApplicationException;
import com.farm.model.Mating;
import com.farm.service.IMatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/matings")
public class MatingController {

    @Autowired
    private IMatingService matingService;

    @GetMapping(value="")
    public List<Mating> listAllWeights() {
        return matingService.findAll();
    }

    @GetMapping(value= "/animal/{id}")
    public ResponseEntity<List<Mating>> getMatingsByAnimalId(@PathVariable("id") int id) {
        List<Mating> weightsByType = matingService.findAllByAnimalId(id);
        return new ResponseEntity(weightsByType, HttpStatus.OK);
    }

    @GetMapping(value= "/date/{date}")
    public ResponseEntity<List<Mating>> getMatingsByDate(@PathVariable ("date") LocalDate date) {
        List<Mating> weightsByType = matingService.findAllByDate(date);
        return new ResponseEntity(weightsByType, HttpStatus.OK);
    }

    @GetMapping(value= "/{id}")
    public ResponseEntity<Mating> getMatingById(@PathVariable("id") int id) {
        Mating weightById = matingService.findById(id);
        return new ResponseEntity(weightById, HttpStatus.OK);
    }

    @PostMapping(value="")
    public ResponseEntity<Mating> saveNewMating( @RequestBody Mating matingBody) throws ApplicationException, IllegalAccessException, NoSuchFieldException {
        Mating weightSaved = matingService.save(matingBody);
        return new ResponseEntity(weightSaved, HttpStatus.OK);
    }

    @PostMapping(value="/{id}")
    public ResponseEntity<Mating> updateMating(@PathVariable("id") int id, @RequestBody Mating matingBody) throws ApplicationException, IllegalAccessException, NoSuchFieldException{
        Mating weightSaved = matingService.update(id, matingBody);
        return new ResponseEntity(weightSaved, HttpStatus.OK);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<String> deleteMatingById(@PathVariable("id") int id) throws ApplicationException {
        matingService.deleteById(id);
        return new ResponseEntity("Weight deleted successfully", HttpStatus.OK);
    }
}
