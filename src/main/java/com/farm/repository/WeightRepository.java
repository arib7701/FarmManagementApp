package com.farm.repository;

import com.farm.dao.AnimalWeightEntity;
import org.springframework.data.repository.CrudRepository;

public interface WeightRepository extends CrudRepository<AnimalWeightEntity, Integer> {
}
