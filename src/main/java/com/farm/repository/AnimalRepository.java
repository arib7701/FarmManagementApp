package com.farm.repository;

import com.farm.dao.AnimalEntity;
import com.farm.dao.AnimalTypeEntity;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface AnimalRepository extends CrudRepository<AnimalEntity, Integer> {

    List<AnimalEntity> findByAnimalType(int typeId);
    List<AnimalEntity> findByAnimalTypeAndDateDeathIsAfter(int typeId, Date date);
    List<AnimalEntity> findByAnimalTypeAndDateDeathIsNull(int typeId);
    List<AnimalEntity> findByAnimalTypeAndDateDeathIsNullAndDateDepartureIsNull(int typeId);

    List<AnimalEntity> findByFatherId(int fatherId);
    List<AnimalEntity> findByMotherId(int motherId);

    List<AnimalEntity> findByAnimalSex(String sex);
    List<AnimalEntity> findByDateBirthContaining(int year);

    List<AnimalEntity> findByDateArrival(Date arrival);
    List<AnimalEntity> findByDateDeparture(Date departure);

    AnimalEntity findByAnimalId(int id);
    AnimalEntity findByAnimalName(String name);

    void deleteByAnimalId(int id);
    void deleteByAnimalName(String name);
}

