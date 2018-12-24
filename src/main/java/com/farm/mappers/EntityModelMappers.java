package com.farm.mappers;

import com.farm.dao.AnimalEntity;
import com.farm.model.Animal;

import java.util.ArrayList;
import java.util.List;

public class EntityModelMappers {

    private EntityModelMappers() {
    }

    public static List<Animal> parseAnimalList(List<AnimalEntity> animalEntityList) {

        List<Animal> animalList = null;

        if(animalEntityList != null) {
            animalList = new ArrayList<>();

            for(AnimalEntity animalEntity : animalEntityList) {
                Animal animal = parseAnimalEntity(animalEntity);
                animalList.add(animal);
            }
        }

        return animalList;
    }

    public static Animal parseAnimalEntity(AnimalEntity animalEntity) {

        Animal animal = null;

        try {

            animal = new Animal();
            animal.setId(animalEntity.getAnimalId());
            animal.setName(animalEntity.getAnimalName());
            animal.setSex(animalEntity.getAnimalSex());
            animal.setBirth(animalEntity.getDateBirth().toLocalDate());
            animal.setDeath(animalEntity.getDateDeath().toLocalDate());
            animal.setArrival(animalEntity.getDateArrival().toLocalDate());
            animal.setDeparture(animalEntity.getDateDeparture().toLocalDate());
            animal.setType(animalEntity.getAnimalTypeByAnimalType().getTypeName());
            animal.setMotherId(animalEntity.getMotherId());
            animal.setFatherId(animalEntity.getFatherId());
            animal.setResearch(animalEntity.getIsResearch());
            animal.setWeights(animalEntity.getAnimalWeightsByAnimalId());
            animal.setVaccines(animalEntity.getAnimalVaccinesByAnimalId());
            this.weights = null;
            this.vaccines = null;
            this.deliveries = null;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return animal;
    }

    public static AnimalEntity parseAnimal(Animal animal) {

        AnimalEntity animalEntity = null;

        try {

            animalEntity = new AnimalEntity();
            animalEntity.setAnimalId(animal.getId());
            animalEntity.setAnimalName(animal.getName());


        } catch (Exception e) {
            e.printStackTrace();
        }

        return animalEntity;
    }
}
