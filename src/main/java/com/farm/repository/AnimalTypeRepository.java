package com.farm.repository;

import com.farm.dao.AnimalTypeEntity;
import org.springframework.data.repository.CrudRepository;

public interface AnimalTypeRepository extends CrudRepository<AnimalTypeEntity, Integer> {

    AnimalTypeEntity findByTypeId(int id);
    AnimalTypeEntity findByTypeName(String name);
    void deleteByTypeName(String name);
}
