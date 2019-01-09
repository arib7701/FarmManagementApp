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

    @RequestMapping(value="", method = RequestMethod.GET)
    public List<Weight> listAllWeights() {
        return weightService.findAll();
    }

    @RequestMapping(value= "/animal/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Weight>> getWeightsByAnimalId(@PathVariable ("id") int id) {
        List<Weight> weightsByType = weightService.findByAnimalId(id);
        return new ResponseEntity(weightsByType, HttpStatus.OK);
    }

    @RequestMapping(value= "/date/{date}", method = RequestMethod.GET)
    public ResponseEntity<List<Weight>> getWeightsByDate(@PathVariable ("date") Date date) {
        List<Weight> weightsByType = weightService.findByDate(date);
        return new ResponseEntity(weightsByType, HttpStatus.OK);
    }

    @RequestMapping(value= "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Weight> getAnimalById(@PathVariable("id") int id) {
        Weight weightById = weightService.findById(id);
        return new ResponseEntity(weightById, HttpStatus.OK);
    }
}
