package com.farm.service.implementation;

import com.farm.dao.AnimalWeightEntity;
import com.farm.exceptions.ApplicationException;
import com.farm.model.Weight;
import com.farm.repository.WeightRepository;
import com.farm.service.IWeightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.farm.mappers.EntityModelMappers.parseWeight;
import static com.farm.mappers.EntityModelMappers.parseWeightEntity;

@Service
public class WeightServiceImplementation implements IWeightService {

    @Autowired
    private WeightRepository weightRepository;

    @Override
    public List<Weight> findAll() {
        return null;
    }

    @Override
    public List<Weight> findByAnimalId(int animalId) {

        List<AnimalWeightEntity> animalWeightEntities = weightRepository.findByAnimalIdOrderByWeightDate(animalId);
        List<Weight> weights = null;

        if(animalWeightEntities != null) {

            weights = new ArrayList<>();

            for (AnimalWeightEntity animalWeightEntity : animalWeightEntities) {
                weights.add(parseWeightEntity(animalWeightEntity));
            }
        }

        return weights;
    }

    @Override
    public List<Weight> findByDate(Date date) {
        return null;
    }

    @Override
    public Weight findById(int id) {
        return null;
    }

    @Override
    public Weight save(Weight weight) throws ApplicationException{

        Weight weightSaved;

        if(checkWeightValid(weight.getMeasure())) {

            AnimalWeightEntity weightEntity = parseWeight(weight);
            AnimalWeightEntity weightEntitySaved = weightRepository.save(weightEntity);
            weightSaved = parseWeightEntity(weightEntitySaved);
        }
        else {
            throw new ApplicationException("Error: the weight measurement is invalid.");
        }

        return weightSaved;
    }

    @Override
    public Weight update(int id, Weight weight) throws ApplicationException {

        AnimalWeightEntity weightEntity = weightRepository.findById(id).orElse(null);
        Weight weightUpdated;

        if(weightEntity != null) {

            if(checkWeightValid(weight.getMeasure())) {

                weightEntity = parseWeight(weight);
                AnimalWeightEntity weightEntityUpdate = weightRepository.save(weightEntity);
                weightUpdated = parseWeightEntity(weightEntityUpdate);
            }
            else {
                throw new ApplicationException("Error: the weight measurement is invalid.");
            }
        }
        else {
            throw new ApplicationException("Error: the weight does not exist.");
        }

        return weightUpdated;

    }

    @Override
    public boolean deleteById(int id) {
        weightRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean deleteByAnimalId(int animalId) throws ApplicationException {

        List<AnimalWeightEntity> weightEntities = weightRepository.findByAnimalId(animalId);
        boolean deleted;

        if(weightEntities != null) {

            for(AnimalWeightEntity weightEntity : weightEntities) {
                weightRepository.deleteById(weightEntity.getWeightId());
            }
            deleted = true;
        }
        else {
            throw new ApplicationException("Error: the weights do not exist.");
        }

        return deleted;
    }

    @Override
    public void deleteByDate(Date date) {

    }

    private boolean checkWeightValid(double weightMeasure) {

        return (weightMeasure >= 0 && weightMeasure <= 100);
    }
}
