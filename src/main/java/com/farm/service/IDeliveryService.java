package com.farm.service;

import com.farm.exceptions.ApplicationException;
import com.farm.model.Delivery;

import java.time.LocalDate;
import java.util.List;

public interface IDeliveryService {

    List<Delivery> findAll();
    List<Delivery> findAllByAnimalId(int id);
    List<Delivery> findAllByDate(LocalDate date);

    Delivery findById(int id);

    Delivery save(Delivery delivery) throws ApplicationException, IllegalAccessException, NoSuchFieldException;
    Delivery update(int id, Delivery delivery) throws ApplicationException, IllegalAccessException, NoSuchFieldException;

    boolean deleteById(int id) throws ApplicationException;
}
