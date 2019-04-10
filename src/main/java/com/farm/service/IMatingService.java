package com.farm.service;

import com.farm.exceptions.ApplicationException;
import com.farm.model.Mating;

import java.time.LocalDate;
import java.util.List;

public interface IMatingService {

    List<Mating> findAll();
    List<Mating> findAllByAnimalId(int id);
    List<Mating> findAllByDate(LocalDate date);

    Mating findById(int id);

    Mating save(Mating mating) throws ApplicationException, IllegalAccessException, NoSuchFieldException;
    Mating update(int id, Mating mating) throws ApplicationException, IllegalAccessException, NoSuchFieldException;

    boolean deleteById(int id) throws ApplicationException;
}
