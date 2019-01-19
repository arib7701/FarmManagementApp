package com.farm.service.implementation;

import com.farm.dao.AnimalWeightEntity;
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
    public Weight save(Weight weight) {
        AnimalWeightEntity weightEntity = parseWeight(weight);
        AnimalWeightEntity weightEntitySaved = weightRepository.save(weightEntity);
        return parseWeightEntity(weightEntitySaved);
    }

    @Override
    public Weight update(int id, Weight weight) {

        AnimalWeightEntity weightEntity = weightRepository.findById(id).orElse(null);
        Weight weightUpdated = null;

        if(weightEntity != null) {
            weightEntity = parseWeight(weight);
            AnimalWeightEntity weightEntityUpdate = weightRepository.save(weightEntity);
            weightUpdated =parseWeightEntity(weightEntityUpdate);
        }

        return weightUpdated;

    }

    @Override
    public void deleteById(int id) {
        weightRepository.deleteById(id);
    }

    @Override
    public void deleteByAnimalId(int animalId) {
        List<AnimalWeightEntity> weightEntities = weightRepository.findByAnimalId(animalId);

        if(weightEntities != null) {

            for(AnimalWeightEntity weightEntity : weightEntities) {
                weightRepository.deleteById(weightEntity.getWeightId());
            }
        }
    }

    @Override
    public void deleteByDate(Date date) {

    }
}
