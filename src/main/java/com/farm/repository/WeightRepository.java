package com.farm.repository;

import com.farm.dao.AnimalWeightEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WeightRepository extends CrudRepository<AnimalWeightEntity, Integer> {

    List<AnimalWeightEntity> findByAnimalIdOrderByWeightDate(int id);
    List<AnimalWeightEntity> findByAnimalId(int id);
}
