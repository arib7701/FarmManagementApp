package com.farm.service.implementation;

import com.farm.dao.AnimalTypeEntity;
import com.farm.exceptions.ApplicationException;
import com.farm.model.AnimalType;
import com.farm.repository.AnimalTypeRepository;
import com.farm.service.IAnimalTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.farm.mappers.EntityModelMappers.*;

@Service
public class AnimalTypeServiceImplementation implements IAnimalTypeService {

    @Autowired
    private AnimalTypeRepository animalTypeRepository;


    @Override
    public List<AnimalType> findAll() {
        List<AnimalTypeEntity> animalTypeEntityList = (List<AnimalTypeEntity>) animalTypeRepository.findAll();
        return parseAnimalTypeList(animalTypeEntityList);
    }

    @Override
    public AnimalType findById(int id) {
        AnimalTypeEntity animalTypeEntity = animalTypeRepository.findByTypeId(id);
        return parseAnimalTypeEntity(animalTypeEntity);
    }

    @Override
    public AnimalType findByName(String name) {
        AnimalTypeEntity animalTypeEntity = animalTypeRepository.findByTypeName(name);
        return parseAnimalTypeEntity(animalTypeEntity);
    }

    @Override
    public AnimalType save(AnimalType animalType) {
        AnimalTypeEntity animalTypeEntity = parseAnimalType(animalType);
        AnimalTypeEntity animalTypeEntitySaved = animalTypeRepository.save(animalTypeEntity);
        return parseAnimalTypeEntity(animalTypeEntitySaved);
    }

    @Override
    public AnimalType update(int id, AnimalType animalType) throws ApplicationException  {

        AnimalTypeEntity animalTypeEntity = animalTypeRepository.findByTypeId(id);
        AnimalType animalTypeUpdated;

        if(animalTypeEntity != null) {
            animalTypeEntity = parseAnimalType(animalType);
            AnimalTypeEntity animalTypeEntityUpdate = animalTypeRepository.save(animalTypeEntity);
            animalTypeUpdated = parseAnimalTypeEntity(animalTypeEntityUpdate);
        }
        else {
            throw new ApplicationException("Error: Animal Type does not exist");
        }

        return animalTypeUpdated;
    }

    @Override
    public void deleteById(int id) {
        animalTypeRepository.deleteById(id);
    }

    @Override
    public void deleteByName(String name) {
        animalTypeRepository.deleteByTypeName(name);
    }
}
