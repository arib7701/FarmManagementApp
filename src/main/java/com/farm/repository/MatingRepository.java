package com.farm.repository;

import com.farm.dao.AnimalMatingEntity;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.List;

public interface MatingRepository extends CrudRepository<AnimalMatingEntity, Integer> {

    List<AnimalMatingEntity> findByFatherIdOrderByMatingDate(int id);
    List<AnimalMatingEntity> findByMotherIdOrderByMatingDate(int id);
    List<AnimalMatingEntity> findByMatingDate(Date date);
}
