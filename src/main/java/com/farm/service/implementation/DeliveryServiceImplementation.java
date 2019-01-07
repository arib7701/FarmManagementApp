package com.farm.service.implementation;

import com.farm.dao.AnimalDeliveryEntity;
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


    @Override
    public List<Delivery> findAll() {
        List<AnimalDeliveryEntity> animalDeliveryEntityList = (List<AnimalDeliveryEntity>) deliveryRepository.findAll();
        return parseDeliveryList(animalDeliveryEntityList);
    }

    @Override
    public List<Delivery> findAllByMotherId(int id) {
        List<AnimalDeliveryEntity> animalDeliveryEntityList = deliveryRepository.findByMotherId(id);
        return parseDeliveryList(animalDeliveryEntityList);
    }

    @Override
    public List<Delivery> findAllByFatherId(int id) {
        List<AnimalDeliveryEntity> animalDeliveryEntityList = deliveryRepository.findByFatherId(id);
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
    public void deleteById(int id) {
        deliveryRepository.deleteById(id);
    }

    @Override
    public void deleteByName(String name) {

    }
}
