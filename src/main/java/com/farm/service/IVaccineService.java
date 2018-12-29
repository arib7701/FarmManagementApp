package com.farm.service;

import com.farm.model.Vaccine;

import java.time.LocalDate;
import java.util.List;

public interface IVaccineService {

    List<Vaccine> findAll();
    List<Vaccine> findAllByDate(LocalDate date);
    List<Vaccine> findAllByType(String type);

    Vaccine findById(int id);

    Vaccine save(Vaccine vaccine);

    void deleteById(int id);
    void deleteByAnimalId(int id);
}
