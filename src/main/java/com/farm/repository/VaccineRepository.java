package com.farm.repository;

import com.farm.dao.AnimalVaccineEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface VaccineRepository extends CrudRepository<AnimalVaccineEntity, Integer> {

    List<AnimalVaccineEntity> findByVaccineDate(Date date);
    List<AnimalVaccineEntity> findByVaccineType(String type);
    void deleteByAnimalId(int id);
}
