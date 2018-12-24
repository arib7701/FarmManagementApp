package com.farm.service;

import com.farm.model.Delivery;

import java.time.LocalDate;
import java.util.List;

public interface IDeliveryService {

    List<Delivery> findAll();
    List<Delivery> findAllByMotherId(int id);
    List<Delivery> findAllByFatherId(int id);
    List<Delivery> findAllByAnimalName(String name);
    List<Delivery> findAllByDate(LocalDate date);

    Delivery findById(int id);

    Delivery save(Delivery delivery);

    void deleteById(int id);
    void deleteByName(String name);
}
