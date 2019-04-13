package com.farm.service.implementation;

import com.farm.dao.AnimalDeliveryEntity;
import com.farm.exceptions.ApplicationException;
import com.farm.model.Animal;
import com.farm.model.AnimalType;
import com.farm.model.Delivery;
import com.farm.repository.DeliveryRepository;
import com.farm.service.IDeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.farm.utils.AnimalsUtils.*;

import java.time.LocalDate;
import java.util.List;

import static com.farm.mappers.EntityModelMappers.*;
import static com.farm.utils.AnimalsUtils.checkAnimalAlive;
import static com.farm.utils.AnimalsUtils.checkCorrectSexAndAgeAndState;

@Service
public class DeliveryServiceImplementation implements IDeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    private AnimalServiceImplementation animalServiceImplementation;

    @Autowired
    private AnimalTypeServiceImplementation animalTypeServiceImplementation;

    @Override
    public List<Delivery> findAll() {
        List<AnimalDeliveryEntity> animalDeliveryEntityList = (List<AnimalDeliveryEntity>) deliveryRepository.findAll();
        return parseDeliveryList(animalDeliveryEntityList);
    }

    @Override
    public List<Delivery> findAllByAnimalId(int id) {

        Animal animal = animalServiceImplementation.findById(id);
        List<AnimalDeliveryEntity> animalDeliveryEntityList;

        if(animal.getSex().equals("F")) {
            animalDeliveryEntityList = deliveryRepository.findByMotherIdOrderByDeliveryDate(id);
        } else {
            animalDeliveryEntityList = deliveryRepository.findByFatherIdOrderByDeliveryDate(id);
        }

        return parseDeliveryList(animalDeliveryEntityList);
    }

    @Override
    public List<Delivery> findAllByDate(LocalDate date) {
        List<AnimalDeliveryEntity> animalDeliveryEntityList = deliveryRepository.findByDeliveryDate(java.sql.Date.valueOf(date));
        return parseDeliveryList(animalDeliveryEntityList);
    }

    @Override
    public Delivery findById(int id) {
        AnimalDeliveryEntity animalDeliveryEntity = deliveryRepository.findById(id).orElse(null);
        return parseDeliveryEntity(animalDeliveryEntity);
    }

    @Override
    public Delivery save(Delivery delivery) throws ApplicationException, IllegalAccessException, NoSuchFieldException {

        Delivery deliverySaved;

        if(!checkAnimalAlive(delivery, animalServiceImplementation)) {
            throw new ApplicationException("Error: the parents are not alive or have been sold.");
        }

        if (!checkCorrectSexAndAgeAndState(delivery, animalServiceImplementation, animalTypeServiceImplementation)) {
            throw new ApplicationException("Error: the sex or age or state of the parents are invalid.");
        }

        AnimalDeliveryEntity deliveryEntity = parseDelivery(delivery);
        AnimalDeliveryEntity deliveryEntitySaved = deliveryRepository.save(deliveryEntity);
        deliverySaved = parseDeliveryEntity(deliveryEntitySaved);

        return deliverySaved;
    }

    @Override
    public Delivery update(int id, Delivery delivery) throws ApplicationException, IllegalAccessException, NoSuchFieldException {

        AnimalDeliveryEntity animalDeliveryEntity = deliveryRepository.findById(id).orElse(null);
        Delivery deliveryUpdated;

        if(animalDeliveryEntity != null) {

            deliveryUpdated = save(delivery);
        }
        else {
            throw new ApplicationException("Error: the delivery does not exist.");
        }

        return  deliveryUpdated;

    }

    @Override
    public boolean deleteById(int id) throws ApplicationException {

        AnimalDeliveryEntity animalDeliveryEntity = deliveryRepository.findById(id).orElse(null);

        if(animalDeliveryEntity != null) {
            deliveryRepository.deleteById(id);
        }
        else {
            throw new ApplicationException("Error: the delivery does not exist.");
        }

        return true;

    }
}
