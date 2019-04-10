package com.farm.mappers;

import com.farm.dao.*;
import com.farm.model.*;

import java.sql.Date;
import java.time.LocalDate;
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
            animalType.setWeeksGestation(animalTypeEntity.getWeeksGestation() == null? 0: animalTypeEntity.getWeeksGestation());
            animalType.setMonthsMaturity(animalTypeEntity.getMonthsMaturity() == null? 0: animalTypeEntity.getMonthsMaturity());
            animalType.setMinimumWeeksBetweenGestation(animalTypeEntity.getWeeksBetweenGestation() == null? 0: animalTypeEntity.getWeeksBetweenGestation());
            animalType.setMinimumWeeksSuckling(animalTypeEntity.getWeeksSuckling() == null? 0: animalTypeEntity.getWeeksSuckling());
            animalType.setRetirementYearsMale(animalTypeEntity.getRetirementYearsMale());
            animalType.setRetirementYearsFemale(animalTypeEntity.getRetirementYearsFemale());

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
            animalTypeEntity.setTypeImg(animalType.getImageUrl());
            animalTypeEntity.setWeeksGestation(animalType.getWeeksGestation());
            animalTypeEntity.setMonthsMaturity(animalType.getMonthsMaturity());
            animalTypeEntity.setWeeksBetweenGestation(animalType.getMinimumWeeksBetweenGestation());
            animalTypeEntity.setWeeksSuckling(animalType.getMinimumWeeksSuckling());
            animalTypeEntity.setRetirementYearsMale(animalType.getRetirementYearsMale());
            animalTypeEntity.setRetirementYearsFemale(animalType.getRetirementYearsFemale());


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
            animal.setBarn(animalEntity.getBarn());
            animal.setType(animalEntity.getAnimalType());
            animal.setSex(animalEntity.getAnimalSex());
            animal.setBirth(checkNullSQLDate(animalEntity.getDateBirth()));
            animal.setDeath(checkNullSQLDate(animalEntity.getDateDeath()));
            animal.setArrival(checkNullSQLDate(animalEntity.getDateArrival()));
            animal.setDeparture(checkNullSQLDate(animalEntity.getDateDeparture()));
            animal.setDeathCause(animalEntity.getDeathCause());
            animal.setMotherId(animalEntity.getMotherId());
            animal.setFatherId(animalEntity.getFatherId());
            animal.setIsResearch(animalEntity.getIsResearch());
            animal.setState(animalEntity.getState());

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
            animalEntity.setBarn(animal.getBarn());
            animalEntity.setAnimalType(animal.getType());
            animalEntity.setAnimalSex(animal.getSex());
            animalEntity.setDateBirth(checkNullLocalDate(animal.getBirth()));
            animalEntity.setDateDeath(checkNullLocalDate(animal.getDeath()));
            animalEntity.setDateArrival(checkNullLocalDate(animal.getArrival()));
            animalEntity.setDateDeparture(checkNullLocalDate(animal.getDeparture()));
            animalEntity.setDeathCause(animal.getDeathCause());
            animalEntity.setMotherId(animal.getMotherId());
            animalEntity.setFatherId(animal.getFatherId());
            animalEntity.setIsResearch(animal.getIsResearch());
            animalEntity.setState(animal.getState());


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
            delivery.setFatherId(deliveryEntity.getFatherId());
            delivery.setMotherId(deliveryEntity.getMotherId());

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
            animalDeliveryEntity.setFatherId(delivery.getFatherId());
            animalDeliveryEntity.setMotherId(delivery.getMotherId());

        } catch (Exception e){
            e.printStackTrace();
        }

        return animalDeliveryEntity;
    }

    public static List<Mating> parseMatingList(List<AnimalMatingEntity> animalMatingEntityList) {

        List<Mating> matingList = null;

        if(animalMatingEntityList != null) {
            matingList = new ArrayList<>();

            for(AnimalMatingEntity matingEntity : animalMatingEntityList) {
                Mating mating = parseMatingEntity(matingEntity);
                matingList.add(mating);
            }
        }

        return matingList;
    }

    public static Mating parseMatingEntity(AnimalMatingEntity deliveryEntity) {

        Mating delivery = null;

        try {

            delivery = new Mating();
            delivery.setId(deliveryEntity.getMatingId());
            delivery.setDate(deliveryEntity.getMatingDate().toLocalDate());
            delivery.setFatherId(deliveryEntity.getFatherId());
            delivery.setMotherId(deliveryEntity.getMotherId());

        } catch (Exception e){
            e.printStackTrace();
        }

        return delivery;
    }

    public static AnimalMatingEntity parseMating(Mating delivery) {

        AnimalMatingEntity animalMatingEntity = null;

        try {

            animalMatingEntity = new AnimalMatingEntity();
            animalMatingEntity.setMatingId(delivery.getId());
            animalMatingEntity.setMatingDate(java.sql.Date.valueOf(delivery.getDate()));
            animalMatingEntity.setFatherId(delivery.getFatherId());
            animalMatingEntity.setMotherId(delivery.getMotherId());

        } catch (Exception e){
            e.printStackTrace();
        }

        return animalMatingEntity;
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

    public static List<Weight> parseWeightList(List<AnimalWeightEntity> animalWeightEntityList) {

        List<Weight> weightList = null;

        if(animalWeightEntityList != null) {
            weightList = new ArrayList<>();

            for(AnimalWeightEntity weightEntity : animalWeightEntityList) {
                Weight weight = parseWeightEntity(weightEntity);
                weightList.add(weight);
            }
        }

        return weightList;
    }

    public static Weight parseWeightEntity(AnimalWeightEntity weightEntity) {

        Weight weight = null;

        try {

            weight = new Weight();
            weight.setId(weightEntity.getWeightId());
            weight.setDate(weightEntity.getWeightDate().toLocalDate());
            weight.setMeasure(weightEntity.getWeightNumber());
            weight.setAnimalId(weightEntity.getAnimalId());

        } catch (Exception e){
            e.printStackTrace();
        }

        return weight;
    }

    public static AnimalWeightEntity parseWeight(Weight weight) {

        AnimalWeightEntity animalWeightEntity = null;

        try {

            animalWeightEntity = new AnimalWeightEntity();
            animalWeightEntity.setWeightId(weight.getId());
            animalWeightEntity.setWeightDate(java.sql.Date.valueOf(weight.getDate()));
            animalWeightEntity.setWeightNumber(weight.getMeasure());
            animalWeightEntity.setAnimalId(weight.getAnimalId());

        } catch (Exception e){
            e.printStackTrace();
        }

        return animalWeightEntity;
    }


    private static LocalDate checkNullSQLDate(Date dateSQL) {

        LocalDate localDate = null;

        if(dateSQL != null){
            localDate = dateSQL.toLocalDate();
        }

        return localDate;
    }

    private static Date checkNullLocalDate(LocalDate localDate) {

        Date dateSql = null;

        if(localDate != null){
            dateSql = java.sql.Date.valueOf(localDate);
        }

        return dateSql;
    }
}
