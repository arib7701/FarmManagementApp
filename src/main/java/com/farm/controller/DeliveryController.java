package com.farm.controller;

import com.farm.exceptions.ApplicationException;
import com.farm.model.Delivery;
import com.farm.service.IDeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/deliveries")
public class DeliveryController {

    @Autowired
    private IDeliveryService deliveryService;

    @GetMapping(value="")
    public List<Delivery> listAllWeights() {
        return deliveryService.findAll();
    }

    @GetMapping(value= "/animal/{id}")
    public ResponseEntity<List<Delivery>> getDeliveriesByAnimalId(@PathVariable("id") int id) {
        List<Delivery> weightsByType = deliveryService.findAllByAnimalId(id);
        return new ResponseEntity(weightsByType, HttpStatus.OK);
    }

    @GetMapping(value= "/date/{date}")
    public ResponseEntity<List<Delivery>> getDeliveriesByDate(@PathVariable ("date") LocalDate date) {
        List<Delivery> weightsByType = deliveryService.findAllByDate(date);
        return new ResponseEntity(weightsByType, HttpStatus.OK);
    }

    @GetMapping(value= "/{id}")
    public ResponseEntity<Delivery> getDeliveryById(@PathVariable("id") int id) {
        Delivery weightById = deliveryService.findById(id);
        return new ResponseEntity(weightById, HttpStatus.OK);
    }

    @PostMapping(value="")
    public ResponseEntity<Delivery> saveNewDelivery( @RequestBody Delivery deliveryBody) throws ApplicationException {
        Delivery weightSaved = deliveryService.save(deliveryBody);
        return new ResponseEntity(weightSaved, HttpStatus.OK);
    }

    @PostMapping(value="/{id}")
    public ResponseEntity<Delivery> updateDelivery(@PathVariable("id") int id, @RequestBody Delivery deliveryBody) throws ApplicationException{
        Delivery weightSaved = deliveryService.update(id, deliveryBody);
        return new ResponseEntity(weightSaved, HttpStatus.OK);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<String> deleteDeliveryById(@PathVariable("id") int id) throws ApplicationException {
        boolean deleted = deliveryService.deleteById(id);
        return new ResponseEntity("Weight deleted successfully", HttpStatus.OK);
    }
}
