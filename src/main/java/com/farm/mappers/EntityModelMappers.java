package com.farm.mappers;

import com.farm.dao.AnimalDeliveryEntity;
import com.farm.dao.AnimalEntity;
import com.farm.model.Animal;
import com.farm.model.Delivery;

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
            animalEntity.setMotherId(animal.getMotherId());
            animalEntity.setFatherId(animal.getFatherId());


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
}
