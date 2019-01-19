package com.farm.service;

import com.farm.exceptions.ApplicationException;
import com.farm.model.Animal;

import java.util.Date;
import java.util.List;

public interface IAnimalService {

    List<Animal> findAll();
    List<Animal> findByTypeId(int type);
    List<Animal> findByType(int animalTypeId);

    List<Animal> findByAge(int age);
    List<Animal> findBySex(String sex);

    List<Animal> findByArrivalDate(Date date);
    List<Animal> findByDepartureDate(Date date);

    List<Animal> findByMotherId(int id);
    List<Animal> findByFatherId(int id);

    Animal findById(int id);
    Animal findByName(String name);

    Animal save(Animal animal) throws ApplicationException;
    Animal update(int id, Animal animal) throws ApplicationException;

    boolean deleteById(int id) throws ApplicationException;
    void deleteByName(String name);

}
