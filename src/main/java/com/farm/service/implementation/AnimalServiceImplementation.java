package com.farm.service.implementation;

import com.farm.dao.AnimalEntity;
import com.farm.dao.AnimalTypeEntity;
import com.farm.exceptions.ApplicationException;
import com.farm.model.Animal;
import com.farm.model.Weight;
import com.farm.repository.AnimalRepository;
import com.farm.repository.AnimalTypeRepository;
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

    private static final String TEEN = "teen";
    private static final String PREGNANT = "pregnant";
    private static final String NURSING = "nursing";
    private static final String RESTING = "resting";
    private static final String RETIRED = "retired";
    private static final String SUPERMALE = "supermale";
    private static final String DEAD = "dead";
    private static final String SOLD = "sold";
    private static final String FATTENING = "fattening";

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private AnimalTypeRepository animalTypeRepository;

    @Autowired
    private WeightServiceImplementation weightServiceImplementation;

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

        return handleList(animalEntityList);
    }

    @Override
    public List<Animal> findByTypeDeadLessThanSixMonths(int animalTypeId) {

        LocalDate sixMonthsAgo = LocalDate.now().minusMonths(6);
        List<AnimalEntity> animalEntityList1 = animalRepository.findByAnimalTypeAndDateDeathIsAfter(animalTypeId, java.sql.Date.valueOf(sixMonthsAgo));
        List<AnimalEntity> animalEntityList2 = animalRepository.findByAnimalTypeAndDateDeathIsNull(animalTypeId);

        List<AnimalEntity> animalEntityList = new ArrayList<>();
        animalEntityList.addAll(animalEntityList1);
        animalEntityList.addAll(animalEntityList2);

        return handleList(animalEntityList);
    }

    @Override
    public List<Animal> findByTypeAlive(int animalTypeId) {

        List<AnimalEntity> animalEntityList = animalRepository.findByAnimalTypeAndDateDeathIsNullAndDateDepartureIsNull(animalTypeId);
        return handleList(animalEntityList);
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
        List<Weight> weights = weightServiceImplementation.findByAnimalId(id);
        animal.setWeights(weights);
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

        if(!checkDatesAnimalOK(animal) && !checkDeathCause(animal)) {
            throw new ApplicationException("Error: Some of the dates are invalid");
        }

        if(!checkStateValid(animal)) {
            throw new ApplicationException("Error: The state is invalid");
        }

        AnimalEntity animalEntity = parseAnimal(animal);
        AnimalEntity animalEntitySaved = animalRepository.save(animalEntity);
        animalSaved = parseAnimalEntity(animalEntitySaved);

        return animalSaved;
    }

    @Override
    public Animal update(int id, Animal animal) throws ApplicationException  {

        AnimalEntity animalEntity = animalRepository.findByAnimalId(id);
        Animal animalUpdated;

        if(animalEntity == null) {
            throw new ApplicationException("Error: Animal does not exist");
        }

        if(!checkUpdateStateValid(animal)) {
            throw new ApplicationException("Error: New Animal state not valid");
        }

        animalUpdated = save(animal);

        return animalUpdated;

    }

    @Override
    public boolean deleteById(int id) throws ApplicationException {


        AnimalEntity animalEntity = animalRepository.findByAnimalId(id);
        boolean deleted;

        if(animalEntity != null) {

            if(checkIfHasChildren(animalEntity)) {

                if(weightServiceImplementation.deleteByAnimalId(id)) {
                   animalRepository.deleteById(id);
                   deleted = true;
               }
               else {
                    throw new ApplicationException("Error: Could not delete associated weights");
               }

            } else {
                throw new ApplicationException("Error: This animal has descendants and cannot be deleted");
            }

        } else {
            throw new ApplicationException("Error: Animal does not exist");
        }

        return deleted;
    }

    @Override
    public void deleteByName(String name) {
        animalRepository.deleteByAnimalName(name);
    }

    private boolean checkDatesAnimalOK(Animal animal) {

        LocalDate today = LocalDate.now();
        LocalDate birthDate = animal.getBirth() == null ? today.minusDays(1) : animal.getBirth();
        LocalDate deathDate = animal.getDeath() == null ? today : animal.getDeath();
        LocalDate arrivalDate = animal.getArrival() == null ? birthDate : animal.getArrival();
        LocalDate departureDate = animal.getDeparture() == null ? today : animal.getDeparture();

        return ((deathDate.isAfter(birthDate) || deathDate.isEqual(birthDate))
                && (departureDate.isAfter(arrivalDate) || departureDate.isEqual(arrivalDate))
                && (arrivalDate.isAfter(birthDate) || arrivalDate.isEqual(birthDate))
                && (deathDate.isBefore(today) || deathDate.isEqual(today))
                && (birthDate.isBefore(today) || birthDate.isEqual(today))
        );

    }

    private boolean checkDeathCause(Animal animal) {
        
        return (animal.getDeathCause() == null || (animal.getDeathCause() != null && animal.getDeath() != null));
    }

    private boolean checkStateValid(Animal animal) {

        LocalDate today = LocalDate.now();

        AnimalTypeEntity type = animalTypeRepository.findByTypeId(animal.getType());
        LocalDate maturity = LocalDate.now().minusMonths(type.getMonthsMaturity());
        LocalDate pregnancy = maturity.minusWeeks(type.getWeeksGestation());
        LocalDate nursing = pregnancy.minusWeeks(type.getWeeksSuckling());
        LocalDate resting = pregnancy.minusWeeks(type.getWeeksBetweenGestation());

        LocalDate birthDate = animal.getBirth() == null ? today.minusDays(1) : animal.getBirth();
        LocalDate deathDate = animal.getDeath();
        LocalDate departureDate = animal.getDeparture();

        String state = animal.getState();

        return ((state.equals(TEEN) && birthDate.isAfter(maturity) && deathDate == null && departureDate == null)
                || (state.equals(PREGNANT) && birthDate.isBefore(maturity) && birthDate.isAfter(pregnancy) && deathDate == null && departureDate == null)
                || (state.equals(NURSING) && birthDate.isBefore(maturity) && birthDate.isAfter(nursing) && deathDate == null && departureDate == null)
                || (state.equals(RESTING) && birthDate.isBefore(maturity) && birthDate.isAfter(resting) && deathDate == null && departureDate == null)
                || (state.equals(RETIRED) && birthDate.isBefore(maturity) && deathDate == null && departureDate == null)
                || (state.equals(SUPERMALE) && birthDate.isBefore(maturity) && deathDate == null && departureDate == null)
                || (state.equals(FATTENING) && birthDate.isBefore(maturity) && deathDate == null && departureDate == null));

    }

    private boolean checkUpdateStateValid(Animal animal) {

        String sex = animal.getSex();
        String oldState = animalRepository.findByAnimalId(animal.getId()).getState();
        String newState = animal.getState();
        boolean validChange = false;

        if("F".equals(sex)) {
            switch (oldState) {
                case TEEN:
                    if(newState.equals(TEEN) || newState.equals(PREGNANT) || newState.equals(FATTENING) || newState.equals(DEAD) || newState.equals(SOLD)) {
                        validChange = true;
                    }
                    break;
                case PREGNANT:
                    if(newState.equals(PREGNANT) || newState.equals(NURSING) || newState.equals(DEAD) || newState.equals(SOLD)) {
                        validChange = true;
                    }
                    break;
                case NURSING:
                    if(newState.equals(NURSING) || newState.equals(RESTING) || newState.equals(DEAD) || newState.equals(SOLD)) {
                        validChange = true;
                    }
                    break;
                case RESTING:
                    if(newState.equals(RESTING) || newState.equals(PREGNANT) || newState.equals(RETIRED) || newState.equals(DEAD) || newState.equals(SOLD)) {
                        validChange = true;
                    }
                    break;
                case RETIRED:
                    if(newState.equals(RETIRED) || newState.equals(PREGNANT) || newState.equals(DEAD) || newState.equals(SOLD)) {
                        validChange = true;
                    }
                    break;
                case FATTENING:
                    if(newState.equals(PREGNANT) || newState.equals(FATTENING) || newState.equals(DEAD) || newState.equals(SOLD)) {
                        validChange = true;
                    }
                    break;
                case DEAD:
                    if(newState.equals(DEAD)) {
                        validChange = true;
                    }
                    break;
                case SOLD:
                    if(newState.equals(SOLD)) {
                        validChange = true;
                    }
                    break;
                default:
                    break;
            }
        } else if ("M".equals(sex)) {
            switch (oldState) {
                case TEEN:
                    if(newState.equals(TEEN) || newState.equals(SUPERMALE) || newState.equals(FATTENING) || newState.equals(DEAD) || newState.equals(SOLD)) {
                        validChange = true;
                    }
                    break;
                case SUPERMALE:
                    if(newState.equals(SUPERMALE) || newState.equals(RETIRED) || newState.equals(DEAD) || newState.equals(SOLD)) {
                        validChange = true;
                    }
                    break;
                case RETIRED:
                    if(newState.equals(SUPERMALE) || newState.equals(RETIRED) || newState.equals(DEAD) || newState.equals(SOLD)) {
                        validChange = true;
                    }
                    break;
                case FATTENING:
                    if(newState.equals(SUPERMALE) || newState.equals(FATTENING) || newState.equals(DEAD) || newState.equals(SOLD)) {
                        validChange = true;
                    }
                    break;
                case DEAD:
                    if(newState.equals(DEAD)) {
                        validChange = true;
                    }
                    break;
                case SOLD:
                    if(newState.equals(SOLD)) {
                        validChange = true;
                    }
                    break;
                default:
                    break;
            }
        }

        return validChange;
    }

    private boolean checkIfHasChildren(AnimalEntity animalEntity) {

        List<AnimalEntity> animalEntityListChild;

        if(animalEntity.getAnimalSex().equals("F")) {
            animalEntityListChild = animalRepository.findByMotherId(animalEntity.getAnimalId());
        }
        else {
            animalEntityListChild = animalRepository.findByFatherId(animalEntity.getAnimalId());
        }

        return (animalEntityListChild.isEmpty());

    }

    private List<Animal> handleList(List<AnimalEntity> animalEntityList) {

        List<Animal> animalList = parseAnimalList(animalEntityList);

        for(Animal animal : animalList) {

            List<Weight> weights = weightServiceImplementation.findByAnimalId(animal.getId());
            animal.setWeights(weights);
        }

        return animalList;
    }
}
