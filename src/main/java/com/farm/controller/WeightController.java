package com.farm.controller;

import com.farm.model.Weight;
import com.farm.service.IWeightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/weights")
public class WeightController {

    @Autowired
    private IWeightService weightService;

    @GetMapping(value="")
    public List<Weight> listAllWeights() {
        return weightService.findAll();
    }

    @GetMapping(value= "/animal/{id}")
    public ResponseEntity<List<Weight>> getWeightsByAnimalId(@PathVariable ("id") int id) {
        List<Weight> weightsByType = weightService.findByAnimalId(id);
        return new ResponseEntity(weightsByType, HttpStatus.OK);
    }

    @GetMapping(value= "/date/{date}")
    public ResponseEntity<List<Weight>> getWeightsByDate(@PathVariable ("date") Date date) {
        List<Weight> weightsByType = weightService.findByDate(date);
        return new ResponseEntity(weightsByType, HttpStatus.OK);
    }

    @GetMapping(value= "/{id}")
    public ResponseEntity<Weight> getWeightById(@PathVariable("id") int id) {
        Weight weightById = weightService.findById(id);
        return new ResponseEntity(weightById, HttpStatus.OK);
    }

    @PostMapping(value="")
    public ResponseEntity<Weight> saveNewWeight( @RequestBody Weight weightBody){
        Weight weightSaved = weightService.save(weightBody);
        return new ResponseEntity(weightSaved, HttpStatus.OK);
    }

    @PostMapping(value="/{id}")
    public ResponseEntity<Weight> updateWeight(@PathVariable("id") int id, @RequestBody Weight weightBody){
        Weight weightSaved = weightService.update(id, weightBody);
        return new ResponseEntity(weightSaved, HttpStatus.OK);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<String> deleteWeightById(@PathVariable("id") int id) {
        weightService.deleteById(id);
        return new ResponseEntity("Weight deleted successfully", HttpStatus.OK);
    }

}
