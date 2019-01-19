package com.farm.service;

import com.farm.exceptions.ApplicationException;
import com.farm.model.Weight;

import java.util.Date;
import java.util.List;

public interface IWeightService {

    List<Weight> findAll();
    List<Weight> findByAnimalId(int animalId);
    List<Weight> findByDate(Date date);

    Weight findById(int id);

    Weight save(Weight animal) throws ApplicationException;
    Weight update(int id, Weight weight) throws ApplicationException;

    boolean deleteById(int id);
    boolean deleteByAnimalId(int animalId) throws ApplicationException;
    void deleteByDate(Date date);
}
