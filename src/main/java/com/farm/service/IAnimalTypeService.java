package com.farm.service;

import com.farm.model.AnimalType;

import java.util.List;

public interface IAnimalTypeService {

    List<AnimalType> findAll();
    AnimalType findById(int id);
    AnimalType findByName(String name);

    AnimalType save(AnimalType animalType);

    void deleteById(int id);
    void deleteByName(String name);
}
