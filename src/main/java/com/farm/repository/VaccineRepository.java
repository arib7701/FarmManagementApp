package com.farm.repository;

import org.springframework.data.repository.CrudRepository;

public interface VaccineRepository extends CrudRepository<AnimalVaccineEntity, Integer> {
}
