package com.farm.repository;

import org.springframework.data.repository.CrudRepository;

public interface WeightRepository extends CrudRepository<AnimalWeightEntity, Integer> {
}
