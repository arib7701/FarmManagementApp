package com.farm.repository;

import com.farm.dao.AnimalTypeEntity;
import org.springframework.data.repository.CrudRepository;

public interface TypeRepository extends CrudRepository<AnimalTypeEntity, Integer> {
}
