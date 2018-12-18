package com.farm.repository;

import com.farm.dao.AnimalVaccineEntity;
import org.springframework.data.repository.CrudRepository;

public interface VaccineRepository extends CrudRepository<AnimalVaccineEntity, Integer> {
}
