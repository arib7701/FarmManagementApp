package com.farm.service.implementation;

import com.farm.dao.AnimalEntity;
import com.farm.dao.AnimalTypeEntity;
import com.farm.exceptions.ApplicationException;
import com.farm.model.Animal;
import com.farm.model.Delivery;
import com.farm.model.Mating;
import com.farm.model.Weight;
import com.farm.repository.AnimalRepository;
import com.farm.repository.AnimalTypeRepository;
import com.farm.repository.DeliveryRepository;
import com.farm.repository.MatingRepository;
import com.farm.service.IAnimalService;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

import static com.farm.mappers.EntityModelMappers.*;
import static com.farm.mappers.EntityModelMappers.parseMatingList;

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
    private DeliveryRepository deliveryRepository;

    @Autowired
    private MatingRepository matingRepository;

    @Autowired
    private AnimalTypeRepository animalTypeRepository;

    @Autowired
    private WeightServiceImplementation weightServiceImplementation;

    @Autowired
    private DeliveryServiceImplementation deliveryServiceImplementation;

    @Autowired
    private MatingServiceImplementation matingServiceImplementation;

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
    public List<Animal> findByTypeDeadLessThanThreeMonths(int animalTypeId) {

        LocalDate threeMonthsAgo = LocalDate.now().minusMonths(3);
        List<AnimalEntity> animalEntityList1 = animalRepository.findByAnimalTypeAndDateDeathIsAfter(animalTypeId, java.sql.Date.valueOf(threeMonthsAgo));
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

        List<Delivery> deliveries;
        List<Mating> mating;

        if(animal.getSex().equals("F")) {
            deliveries = parseDeliveryList(deliveryRepository.findByMotherIdOrderByDeliveryDate(id));
            mating = parseMatingList(matingRepository.findByMotherIdOrderByMatingDate(id));
        } else {
            deliveries = parseDeliveryList(deliveryRepository.findByFatherIdOrderByDeliveryDate(id));
            mating = parseMatingList(matingRepository.findByFatherIdOrderByMatingDate(id));
        }

        animal.setDeliveries((deliveries));
        animal.setMatings((mating));

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

        if(!checkStatusParent(animal)) {
            throw new ApplicationException(("Error: The parents have not valid state"));
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

        LocalDate birthDate = animal.getBirth() == null ? today.minusDays(1) : animal.getBirth();
        LocalDate deathDate = animal.getDeath();
        LocalDate departureDate = animal.getDeparture();

        String state = animal.getState();

        return ((state.equals(TEEN) && birthDate.isAfter(maturity) )
                || (state.equals(PREGNANT) && birthDate.isBefore(maturity))
                || (state.equals(NURSING) && birthDate.isBefore(pregnancy))
                || (state.equals(RESTING) && birthDate.isBefore(nursing))
                || (state.equals(RETIRED) && birthDate.isBefore(maturity))
                || (state.equals(SUPERMALE) && birthDate.isBefore(maturity))
                || (state.equals(FATTENING) && birthDate.isBefore(maturity))
                && deathDate == null && departureDate == null);

    }

    private boolean checkUpdateStateValid(Animal animal) {

        String sex = animal.getSex();
        String oldState = animalRepository.findByAnimalId(animal.getId()).getState();
        String newState = animal.getState();
        boolean validChange = false;

        Map<Pair<String, String>, List<String>> validState = new HashMap<>();
        validState.put(new Pair("F", TEEN), Arrays.asList(TEEN, PREGNANT, FATTENING, DEAD, SOLD));
        validState.put(new Pair("F", PREGNANT), Arrays.asList(PREGNANT, NURSING, DEAD, SOLD));
        validState.put(new Pair("F", NURSING), Arrays.asList(NURSING, RESTING, RETIRED, DEAD, SOLD));
        validState.put(new Pair("F", RESTING), Arrays.asList(RESTING, PREGNANT, RETIRED, DEAD, SOLD));
        validState.put(new Pair("F", RETIRED), Arrays.asList(RETIRED, PREGNANT, DEAD, SOLD));
        validState.put(new Pair("F", DEAD), Collections.singletonList(DEAD));
        validState.put(new Pair("F", SOLD), Collections.singletonList(SOLD));
        validState.put(new Pair("F", FATTENING), Arrays.asList(FATTENING, PREGNANT, DEAD, SOLD));

        validState.put(new Pair("M", TEEN), Arrays.asList(TEEN, SUPERMALE, FATTENING, DEAD, SOLD));
        validState.put(new Pair("M", SUPERMALE), Arrays.asList(SUPERMALE, RETIRED, DEAD, SOLD));
        validState.put(new Pair("M", RETIRED), Arrays.asList(RETIRED, SUPERMALE, DEAD, SOLD));
        validState.put(new Pair("M", FATTENING), Arrays.asList(FATTENING, SUPERMALE, DEAD, SOLD));
        validState.put(new Pair("M", DEAD), Collections.singletonList(DEAD));
        validState.put(new Pair("M", SOLD), Collections.singletonList(SOLD));

        if(validState.get(new Pair<>(sex, oldState)).contains(newState)){
            validChange = true;
        }

        return (validChange && checkStateValid(animal));
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

            List<Delivery> deliveries = deliveryServiceImplementation.findAllByAnimalId(animal.getId());
            animal.setDeliveries((deliveries));

            List<Mating> mating = matingServiceImplementation.findAllByAnimalId(animal.getId());
            animal.setMatings((mating));
        }

        return animalList;
    }

    private boolean checkStatusParent(Animal animal) {

        if(animal.getMotherId() != null && animal.getFatherId() != null) {
            AnimalEntity mother = animalRepository.findByAnimalId(animal.getMotherId());
            AnimalEntity father = animalRepository.findByAnimalId(animal.getFatherId());

            return ((mother.getState().equals(PREGNANT) || mother.getState().equals(RETIRED) || mother.getState().equals(RESTING) || mother.getState().equals(NURSING)
                    && (father.getState().equals(SUPERMALE) || father.getState().equals(RETIRED))));
        } else {
            return true;
        }
    }
}
