package com.farm.service.implementation;

import com.farm.model.Weight;
import com.farm.service.IWeightService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class WeightServiceImplementation implements IWeightService {
    @Override
    public List<Weight> findAll() {
        return null;
    }

    @Override
    public List<Weight> findByAnimalId(int animalId) {
        return null;
    }

    @Override
    public List<Weight> findByDate(Date date) {
        return null;
    }

    @Override
    public Weight findById(int id) {
        return null;
    }

    @Override
    public Weight save(Weight animal) {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void deleteByAnimalId(int animalId) {

    }

    @Override
    public void deleteByDate(Date date) {

    }
}
