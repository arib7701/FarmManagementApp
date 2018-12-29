package com.farm.service.implementation;

import com.farm.dao.AnimalVaccineEntity;
import com.farm.model.Vaccine;
import com.farm.repository.VaccineRepository;
import com.farm.service.IVaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static com.farm.mappers.EntityModelMappers.*;

@Service
public class VaccineServiceImplementation implements IVaccineService {

    @Autowired
    private VaccineRepository vaccineRepository;

    @Override
    public List<Vaccine> findAll() {
        List<AnimalVaccineEntity> animalVaccineEntityList = (List<AnimalVaccineEntity>) vaccineRepository.findAll();
        return parseVaccineList(animalVaccineEntityList);
    }

    @Override
    public List<Vaccine> findAllByDate(LocalDate date) {
        List<AnimalVaccineEntity> animalVaccineEntityList = vaccineRepository.findByVaccineDate(java.sql.Date.valueOf(date));
        return parseVaccineList(animalVaccineEntityList);
    }

    @Override
    public List<Vaccine> findAllByType(String type) {
        List<AnimalVaccineEntity> animalVaccineEntityList = vaccineRepository.findByVaccineType(type);
        return parseVaccineList(animalVaccineEntityList);
    }

    @Override
    public Vaccine findById(int id) {
        AnimalVaccineEntity animalVaccineEntity = vaccineRepository.findById(id).orElse(null);
        return parseVaccineEntity(animalVaccineEntity);
    }

    @Override
    public Vaccine save(Vaccine vaccine) {
        AnimalVaccineEntity animalVaccineEntity = parseVaccine(vaccine);
        AnimalVaccineEntity animalVaccineEntitySaved = vaccineRepository.save(animalVaccineEntity);
        return parseVaccineEntity(animalVaccineEntitySaved);
    }

    @Override
    public void deleteById(int id) {
        vaccineRepository.deleteById(id);
    }

    @Override
    public void deleteByAnimalId(int id) {
        vaccineRepository.deleteByAnimalId(id);
    }
}
