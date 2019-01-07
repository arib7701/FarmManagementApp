package com.farm.mappers;

import com.farm.dao.AnimalDeliveryEntity;
import com.farm.dao.AnimalEntity;
import com.farm.dao.AnimalTypeEntity;
import com.farm.dao.AnimalVaccineEntity;
import com.farm.model.Animal;
import com.farm.model.AnimalType;
import com.farm.model.Delivery;
import com.farm.model.Vaccine;

import java.util.ArrayList;
import java.util.List;

public class EntityModelMappers {

    private EntityModelMappers() {
    }

    public static List<AnimalType> parseAnimalTypeList(List<AnimalTypeEntity> animalTypeEntityList) {

        List<AnimalType> animalTypeList = null;

        if(animalTypeEntityList != null) {
            animalTypeList = new ArrayList<>();

            for(AnimalTypeEntity animalTypeEntity : animalTypeEntityList) {
                AnimalType animal = parseAnimalTypeEntity(animalTypeEntity);
                animalTypeList.add(animal);
            }
        }

        return animalTypeList;
    }

    public static AnimalType parseAnimalTypeEntity(AnimalTypeEntity animalTypeEntity) {

        AnimalType animalType = null;

        try {

            animalType = new AnimalType();
            animalType.setId(animalTypeEntity.getTypeId());
            animalType.setName(animalTypeEntity.getTypeName());
            animalType.setImageUrl(animalTypeEntity.getTypeImg());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return animalType;
    }

    public static AnimalTypeEntity parseAnimalType(AnimalType animalType) {

        AnimalTypeEntity animalTypeEntity = null;

        try {

            animalTypeEntity = new AnimalTypeEntity();
            animalTypeEntity.setTypeId(animalType.getId());
            animalTypeEntity.setTypeName(animalType.getName());
            animalTypeEntity.setTypeName(animalType.getImageUrl());


        } catch (Exception e) {
            e.printStackTrace();
        }

        return animalTypeEntity;
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
            animalEntity.setAnimalSex(animal.getSex());
            animalEntity.setDateBirth(java.sql.Date.valueOf(animal.getBirth()));
            animalEntity.setDateDeath(java.sql.Date.valueOf(animal.getDeath()));
            animalEntity.setDateArrival(java.sql.Date.valueOf(animal.getArrival()));
            animalEntity.setDateDeparture(java.sql.Date.valueOf(animal.getDeparture()));


        } catch (Exception e) {
            e.printStackTrace();
        }

        return animalEntity;
    }

    public static List<Delivery> parseDeliveryList(List<AnimalDeliveryEntity> animalDeliveryEntityList) {

        List<Delivery> deliveryList = null;

        if(animalDeliveryEntityList != null) {
            deliveryList = new ArrayList<>();

            for(AnimalDeliveryEntity deliveryEntity : animalDeliveryEntityList) {
                Delivery delivery = parseDeliveryEntity(deliveryEntity);
                deliveryList.add(delivery);
            }
        }

        return deliveryList;
    }

    public static Delivery parseDeliveryEntity(AnimalDeliveryEntity deliveryEntity) {

        Delivery delivery = null;

        try {

            delivery = new Delivery();
            delivery.setId(deliveryEntity.getDeliveryId());
            delivery.setDate(deliveryEntity.getDeliveryDate().toLocalDate());
            delivery.setNumber(deliveryEntity.getDeliveryNumber());

        } catch (Exception e){
            e.printStackTrace();
        }

        return delivery;
    }

    public static AnimalDeliveryEntity parseDelivery(Delivery delivery) {

        AnimalDeliveryEntity animalDeliveryEntity = null;

        try {

            animalDeliveryEntity = new AnimalDeliveryEntity();
            animalDeliveryEntity.setDeliveryId(delivery.getId());
            animalDeliveryEntity.setDeliveryDate(java.sql.Date.valueOf(delivery.getDate()));
            animalDeliveryEntity.setDeliveryNumber(delivery.getNumber());

        } catch (Exception e){
            e.printStackTrace();
        }

        return animalDeliveryEntity;
    }

    public static List<Vaccine> parseVaccineList(List<AnimalVaccineEntity> animalVaccineEntityList) {

        List<Vaccine> vaccineList = null;

        if(animalVaccineEntityList != null) {
            vaccineList = new ArrayList<>();

            for(AnimalVaccineEntity vaccineEntity : animalVaccineEntityList) {
                Vaccine vaccine = parseVaccineEntity(vaccineEntity);
                vaccineList.add(vaccine);
            }
        }

        return vaccineList;
    }

    public static Vaccine parseVaccineEntity(AnimalVaccineEntity vaccineEntity) {

        Vaccine vaccine = null;

        try {

            vaccine = new Vaccine();
            vaccine.setId(vaccineEntity.getVaccineId());
            vaccine.setDate(vaccineEntity.getVaccineDate().toLocalDate());
            vaccine.setQuantity(vaccineEntity.getVaccineQuantity());
            vaccine.setType(vaccineEntity.getVaccineType());

        } catch (Exception e){
            e.printStackTrace();
        }

        return vaccine;
    }

    public static AnimalVaccineEntity parseVaccine(Vaccine vaccine) {

        AnimalVaccineEntity animalVaccineEntity = null;

        try {

            animalVaccineEntity = new AnimalVaccineEntity();
            animalVaccineEntity.setVaccineId(vaccine.getId());
            animalVaccineEntity.setVaccineDate(java.sql.Date.valueOf(vaccine.getDate()));
            animalVaccineEntity.setVaccineQuantity(vaccine.getQuantity());
            animalVaccineEntity.setVaccineType(vaccine.getType());

        } catch (Exception e){
            e.printStackTrace();
        }

        return animalVaccineEntity;
    }
}
