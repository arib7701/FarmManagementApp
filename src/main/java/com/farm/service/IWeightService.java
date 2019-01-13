package com.farm.service;

import com.farm.model.Weight;

import java.util.Date;
import java.util.List;

public interface IWeightService {

    List<Weight> findAll();
    List<Weight> findByAnimalId(int animalId);
    List<Weight> findByDate(Date date);

    Weight findById(int id);

    Weight save(Weight animal);
    Weight update(int id, Weight weight);

    void deleteById(int id);
    void deleteByAnimalId(int animalId);
    void deleteByDate(Date date);
}
