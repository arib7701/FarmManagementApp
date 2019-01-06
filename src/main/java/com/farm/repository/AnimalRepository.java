package com.farm.repository;

import com.farm.dao.AnimalEntity;
import com.farm.dao.AnimalTypeEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface AnimalRepository extends CrudRepository<AnimalEntity, Integer> {

    List<AnimalEntity> findByAnimalType(int typeId);
    List<AnimalEntity> findByAnimalTypeByAnimalType(AnimalTypeEntity typeEntity);

    List<AnimalEntity> findByAnimalByFatherId(int fatherId);
    List<AnimalEntity> findByAnimalByMotherId(int motherId);

    List<AnimalEntity> findByAnimalSex(String sex);
    List<AnimalEntity> findByDateBirthContaining(int year);

    List<AnimalEntity> findByDateArrival(Date arrival);
    List<AnimalEntity> findByDateDeparture(Date departure);

    AnimalEntity findByAnimalId(int id);
    AnimalEntity findByAnimalName(String name);

    void deleteByAnimalId(int id);
    void deleteByAnimalName(String name);
}

