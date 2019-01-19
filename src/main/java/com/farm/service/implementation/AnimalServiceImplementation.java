package com.farm.service.implementation;

import com.farm.dao.AnimalEntity;
import com.farm.dao.AnimalWeightEntity;
import com.farm.exceptions.ApplicationException;
import com.farm.model.Animal;
import com.farm.model.Weight;
import com.farm.repository.AnimalRepository;
import com.farm.repository.WeightRepository;
import com.farm.service.IAnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.farm.mappers.EntityModelMappers.*;

@Service
public class AnimalServiceImplementation implements IAnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private WeightRepository weightRepository;

    @Override
    public List<Animal> findAll() {
        List<AnimalEntity> animalEntityList = (List<AnimalEntity>) animalRepository.findAll();
        return parseAnimalList(animalEntityList);
    }

    @Override
    public List<Animal> findByTypeId(int typeId) {

        List<AnimalEntity> animalEntityList = animalRepository.findByAnimalType(typeId);
        return parseAnimalList(animalEntityList);
    }

    @Override
    public List<Animal> findByType(int animalTypeId) {

        List<AnimalEntity> animalEntityList = animalRepository.findByAnimalType(animalTypeId);
        List<Animal> animalList = parseAnimalList(animalEntityList);

        for(Animal animal : animalList) {

            List<AnimalWeightEntity> animalWeightEntities = weightRepository.findByAnimalId(animal.getId());

            if(animalWeightEntities != null) {

                List<Weight> weights = new ArrayList<>();

                for (AnimalWeightEntity animalWeightEntity : animalWeightEntities) {
                    weights.add(parseWeightEntity(animalWeightEntity));
                }

                animal.setWeights(weights);
            }
        }

        return animalList;
    }

    @Override
    public List<Animal> findByAge(int age) {

        int currentYear = LocalDate.now().getYear();
        int yearSearched = currentYear - age;

        List<AnimalEntity> animalEntityList = animalRepository.findByDateBirthContaining(yearSearched);
        return parseAnimalList(animalEntityList);
    }

    @Override
    public List<Animal> findBySex(String sex) {
        List<AnimalEntity> animalEntityList =  animalRepository.findByAnimalSex(sex);
        return parseAnimalList(animalEntityList);
    }

    @Override
    public List<Animal> findByArrivalDate(Date date) {
        List<AnimalEntity> animalEntityList = animalRepository.findByDateArrival(date);
        return parseAnimalList(animalEntityList);
    }

    @Override
    public List<Animal> findByDepartureDate(Date date) {
        List<AnimalEntity> animalEntityList =  animalRepository.findByDateDeparture(date);
        return parseAnimalList(animalEntityList);
    }

    @Override
    public List<Animal> findByMotherId(int id) {
        List<AnimalEntity> animalEntityList =  animalRepository.findByMotherId(id);
        return parseAnimalList(animalEntityList);
    }

    @Override
    public List<Animal> findByFatherId(int id) {
        List<AnimalEntity> animalEntityList =  animalRepository.findByFatherId(id);
        return parseAnimalList(animalEntityList);
    }

    @Override
    public Animal findById(int id) {
        AnimalEntity animalEntity = animalRepository.findByAnimalId(id);

        Animal animal = parseAnimalEntity(animalEntity);

        List<AnimalWeightEntity> animalWeightEntities = weightRepository.findByAnimalId(animal.getId());

        if(animalWeightEntities != null) {

            List<Weight> weights = new ArrayList<>();

            for (AnimalWeightEntity animalWeightEntity : animalWeightEntities) {
                weights.add(parseWeightEntity(animalWeightEntity));
            }

            animal.setWeights(weights);
        }

        return animal;
    }

    @Override
    public Animal findByName(String name) {
        AnimalEntity animalEntity = animalRepository.findByAnimalName(name);
        return parseAnimalEntity(animalEntity);
    }

    @Override
    public Animal save(Animal animal) throws ApplicationException {

        Animal animalSaved;

        if(checkDatesAnimalOK(animal) && checkDeathCause(animal)) {

            AnimalEntity animalEntity = parseAnimal(animal);
            AnimalEntity animalEntitySaved = animalRepository.save(animalEntity);
            animalSaved = parseAnimalEntity(animalEntitySaved);
        }
        else {
            throw new ApplicationException("Error: Some of the dates are invalid");
        }

        return animalSaved;
    }

    @Override
    public Animal update(int id, Animal animal) throws ApplicationException  {

        AnimalEntity animalEntity = animalRepository.findByAnimalId(id);
        Animal animalUpdated;

        if(animalEntity != null) {

            if(checkDatesAnimalOK(animal) && checkDeathCause(animal)) {
                animalEntity = parseAnimal(animal);
                AnimalEntity animalEntityUpdate = animalRepository.save(animalEntity);
                animalUpdated = parseAnimalEntity(animalEntityUpdate);
            }
            else {
                throw new ApplicationException("Error: Some of the dates are invalid");
            }
        }
        else {
            throw new ApplicationException("Error: Animal does not exist");
        }

        return animalUpdated;

    }

    @Override
    public void deleteById(int id) {

        List<AnimalWeightEntity> weightEntities = weightRepository.findByAnimalId(id);

        if(weightEntities != null) {

            for(AnimalWeightEntity weightEntity : weightEntities) {
                weightRepository.deleteById(weightEntity.getWeightId());
            }
        }

        animalRepository.deleteById(id);
    }

    @Override
    public void deleteByName(String name) {
        animalRepository.deleteByAnimalName(name);
    }

    private boolean checkDatesAnimalOK(Animal animal) {

        LocalDate today = LocalDate.now();
        LocalDate birthDate = animal.getBirth() == null ? today : animal.getBirth();
        LocalDate deathDate = animal.getDeath() == null ? today : animal.getDeath();
        LocalDate arrivalDate = animal.getArrival() == null ? today : animal.getArrival();
        LocalDate departureDate = animal.getDeparture() == null ? today : animal.getDeparture();

        return (deathDate.isAfter(birthDate)
                && departureDate.isAfter(arrivalDate)
                && (arrivalDate.isAfter(birthDate) || arrivalDate.isEqual(birthDate))
                && (deathDate.isBefore(today) || deathDate.isEqual(today))
                && (birthDate.isBefore(today) || birthDate.isEqual(today))
        );

    }

    private boolean checkDeathCause(Animal animal) {

        return (animal.getDeathCause() == null || (animal.getDeathCause() != null && animal.getDeath() != null));
    }

}
