package com.farm.service.implementation;

import com.farm.dao.AnimalDeliveryEntity;
import com.farm.exceptions.ApplicationException;
import com.farm.model.Animal;
import com.farm.model.Delivery;
import com.farm.repository.DeliveryRepository;
import com.farm.service.IDeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static com.farm.mappers.EntityModelMappers.*;

@Service
public class DeliveryServiceImplementation implements IDeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    private AnimalServiceImplementation animalServiceImplementation;

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
    public Delivery save(Delivery delivery) {
        AnimalDeliveryEntity deliveryEntity = parseDelivery(delivery);
        AnimalDeliveryEntity deliveryEntitySaved = deliveryRepository.save(deliveryEntity);
        return parseDeliveryEntity(deliveryEntitySaved);
    }

    @Override
    public Delivery update(int id, Delivery delivery) throws ApplicationException {

        AnimalDeliveryEntity animalDeliveryEntity = deliveryRepository.findById(id).orElse(null);
        Delivery deliveryUpdated;

        if(animalDeliveryEntity != null) {

            AnimalDeliveryEntity deliveryEntity = parseDelivery(delivery);
            AnimalDeliveryEntity deliveryEntitySaved = deliveryRepository.save(deliveryEntity);
            deliveryUpdated = parseDeliveryEntity(deliveryEntitySaved);
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

    @Override
    public void deleteByName(String name) {

    }
}
