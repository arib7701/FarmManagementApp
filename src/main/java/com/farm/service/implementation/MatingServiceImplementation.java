package com.farm.service.implementation;

import com.farm.dao.AnimalMatingEntity;
import com.farm.exceptions.ApplicationException;
import com.farm.model.Animal;
import com.farm.model.Mating;
import com.farm.repository.MatingRepository;
import com.farm.service.IMatingService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

import static com.farm.mappers.EntityModelMappers.*;
import static com.farm.utils.AnimalsUtils.*;

public class MatingServiceImplementation implements IMatingService {


    @Autowired
    private MatingRepository matingRepository;

    @Autowired
    private AnimalServiceImplementation animalServiceImplementation;

    @Autowired
    private AnimalTypeServiceImplementation animalTypeServiceImplementation;

    @Override
    public List<Mating> findAll() {
        List<AnimalMatingEntity> animalMatingEntityList = (List<AnimalMatingEntity>) matingRepository.findAll();
        return parseMatingList(animalMatingEntityList);
    }

    @Override
    public List<Mating> findAllByAnimalId(int id) {

        Animal animal = animalServiceImplementation.findById(id);
        List<AnimalMatingEntity> animalMatingEntityList;

        if(animal.getSex().equals("F")) {
            animalMatingEntityList = matingRepository.findByMotherIdOrderByMatingDate(id);
        } else {
            animalMatingEntityList = matingRepository.findByFatherIdOrderByMatingDate(id);
        }

        return parseMatingList(animalMatingEntityList);
    }

    @Override
    public List<Mating> findAllByDate(LocalDate date) {
        List<AnimalMatingEntity> animalMatingEntityList = matingRepository.findByMatingDate(java.sql.Date.valueOf(date));
        return parseMatingList(animalMatingEntityList);
    }

    @Override
    public Mating findById(int id) {
        AnimalMatingEntity animalMatingEntity = matingRepository.findById(id).orElse(null);
        return parseMatingEntity(animalMatingEntity);
    }

    @Override
    public Mating save(Mating mating) throws ApplicationException, IllegalAccessException, NoSuchFieldException {

        Mating matingSaved;

        if(!checkAnimalAlive(mating, animalServiceImplementation)) {
            throw new ApplicationException("Error: the parents are not alive or have been sold.");
        }

        if (!checkCorrectSexAndAgeAndState(mating, animalServiceImplementation, animalTypeServiceImplementation)) {
            throw new ApplicationException("Error: the sex or age or state of the parents are invalid.");
        }

        AnimalMatingEntity matingEntity = parseMating(mating);
        AnimalMatingEntity matingEntitySaved = matingRepository.save(matingEntity);
        matingSaved = parseMatingEntity(matingEntitySaved);

        return matingSaved;
    }

    @Override
    public Mating update(int id, Mating mating) throws ApplicationException, IllegalAccessException, NoSuchFieldException {

        AnimalMatingEntity animalMatingEntity = matingRepository.findById(id).orElse(null);
        Mating matingUpdated;

        if(animalMatingEntity != null) {

            matingUpdated = save(mating);
        }
        else {
            throw new ApplicationException("Error: the mating does not exist.");
        }

        return  matingUpdated;

    }

    @Override
    public boolean deleteById(int id) throws ApplicationException {

        AnimalMatingEntity animalMatingEntity = matingRepository.findById(id).orElse(null);

        if(animalMatingEntity != null) {
            matingRepository.deleteById(id);
        }
        else {
            throw new ApplicationException("Error: the mating does not exist.");
        }

        return true;

    }
}
