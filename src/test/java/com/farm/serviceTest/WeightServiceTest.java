package com.farm.serviceTest;

import com.farm.dao.AnimalWeightEntity;
import com.farm.exceptions.ApplicationException;
import com.farm.model.Animal;
import com.farm.model.Weight;
import com.farm.repository.WeightRepository;
import com.farm.service.implementation.AnimalServiceImplementation;
import com.farm.service.implementation.WeightServiceImplementation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WeightServiceTest {

    @InjectMocks
    WeightServiceImplementation weightServiceImplementation;

    @Mock
    WeightRepository weightRepository;

    @Mock
    AnimalServiceImplementation animalServiceImplementation;

    @Test
    public void save_WhenMeasureInvalid_ExpectedNull() {

        // GIVEN
        Weight weight = new Weight();
        weight.setDate(LocalDate.now());
        weight.setMeasure(-10.0);
        weight.setAnimalId(1);

        // WHEN
        try {
            weightServiceImplementation.save(weight);
        }
        // THEN
        catch (ApplicationException e) {
            assertTrue("Error: the weight measurement is invalid.".equals(e.getMessage()));
        }
    }

    @Test
    public void save_WhenWeightValid_ExpectedWeight() throws ApplicationException{

        // GIVEN
        Weight weight = new Weight();
        weight.setDate(LocalDate.now());
        weight.setMeasure(10.0);
        weight.setAnimalId(1);

        AnimalWeightEntity animalWeightEntity = new AnimalWeightEntity();
        animalWeightEntity.setWeightDate(java.sql.Date.valueOf(LocalDate.now()));
        animalWeightEntity.setWeightNumber(10.0);
        animalWeightEntity.setAnimalId(1);

        when(weightRepository.save(animalWeightEntity)).thenReturn(animalWeightEntity);

        // WHEN
        Weight weightSaved = weightServiceImplementation.save(weight);
        // THEN
        assertTrue(weightSaved != null && weightSaved.getMeasure() == 10.0);

    }

    @Test
    public void save_WhenAnimalDead_ExpectedNull() throws ApplicationException{

        // GIVEN
        Weight weight = new Weight();
        weight.setDate(LocalDate.now());
        weight.setMeasure(10.0);
        weight.setAnimalId(1);

        AnimalWeightEntity animalWeightEntity = new AnimalWeightEntity();
        animalWeightEntity.setWeightDate(java.sql.Date.valueOf(LocalDate.now()));
        animalWeightEntity.setWeightNumber(10.0);
        animalWeightEntity.setAnimalId(1);

        Animal animal = new Animal(1,"mom", "F", "A1", LocalDate.now(), 1, null, null, "teen");
        animal.setDeath(LocalDate.now().plusDays(1));
        when(animalServiceImplementation.findById(any(int.class))).thenReturn(animal);

        // WHEN
        try {
            weightServiceImplementation.save(weight);
        }
        // THEN
        catch (ApplicationException e) {
            assertTrue("Error: the animal is dead or gone.".equals(e.getMessage()));
        }

    }

    @Test
    public void update_WhenWeighNotExists_ExpectedNull() {

        // GIVEN
        Weight weight = new Weight();
        weight.setId(1);
        weight.setDate(LocalDate.now());
        weight.setMeasure(-10.0);
        weight.setAnimalId(1);

        when(weightRepository.findById(any(int.class))).thenReturn(Optional.empty());

        // WHEN
        try {
            weightServiceImplementation.update(1, weight);
        }
        // THEN
        catch (ApplicationException e) {
            assertTrue("Error: the weight does not exist.".equals(e.getMessage()));
        }

    }

    @Test
    public void update_WhenMeasureInvalid_ExpectedNull() {

        // GIVEN
        Weight weight = new Weight();
        weight.setId(1);
        weight.setDate(LocalDate.now());
        weight.setMeasure(-10.0);
        weight.setAnimalId(1);

        AnimalWeightEntity animalWeightEntity = new AnimalWeightEntity();
        animalWeightEntity.setWeightId(1);
        animalWeightEntity.setWeightDate(java.sql.Date.valueOf(LocalDate.now()));
        animalWeightEntity.setWeightNumber(10.0);
        animalWeightEntity.setAnimalId(1);

        when(weightRepository.findById(any(int.class))).thenReturn(Optional.of(animalWeightEntity));

        // WHEN
        try {
            weightServiceImplementation.update(1, weight);
        }
        // THEN
        catch (ApplicationException e) {
            assertTrue("Error: the weight measurement is invalid.".equals(e.getMessage()));
        }
    }

    @Test
    public void update_WhenWeightValid_ExpectedWeight() throws ApplicationException{

        // GIVEN
        Weight weight = new Weight();
        weight.setId(1);
        weight.setDate(LocalDate.now());
        weight.setMeasure(10.0);
        weight.setAnimalId(1);

        AnimalWeightEntity animalWeightEntity = new AnimalWeightEntity();
        animalWeightEntity.setWeightId(1);
        animalWeightEntity.setWeightDate(java.sql.Date.valueOf(LocalDate.now()));
        animalWeightEntity.setWeightNumber(10.0);
        animalWeightEntity.setAnimalId(1);

        when(weightRepository.findById(any(int.class))).thenReturn(Optional.of(animalWeightEntity));
        when(weightRepository.save(animalWeightEntity)).thenReturn(animalWeightEntity);

        // WHEN
        Weight weightSaved = weightServiceImplementation.update(1, weight);
        // THEN
        assertTrue(weightSaved != null && weightSaved.getMeasure() == 10.0);

    }

    @Test
    public void delete_WhenWeightsNotExist_ExpectedNull() {

        // GIVEN
        when(weightRepository.findByAnimalId(any(int.class))).thenReturn(null);

        // WHEN
        try {
            weightServiceImplementation.deleteByAnimalId(1);
        }
        // THEN
        catch (ApplicationException e) {
            assertTrue("Error: the weights do not exist.".equals(e.getMessage()));
        }
;    }

    @Test
    public void delete_WhenWeightsExist_ExpectedTrue() throws ApplicationException {

        // GIVEN
        AnimalWeightEntity animalWeightEntity1 = new AnimalWeightEntity();
        animalWeightEntity1.setWeightId(1);
        animalWeightEntity1.setWeightDate(java.sql.Date.valueOf(LocalDate.now()));
        animalWeightEntity1.setWeightNumber(10.0);
        animalWeightEntity1.setAnimalId(1);

        AnimalWeightEntity animalWeightEntity2 = new AnimalWeightEntity();
        animalWeightEntity2.setWeightId(2);
        animalWeightEntity2.setWeightDate(java.sql.Date.valueOf(LocalDate.now()));
        animalWeightEntity2.setWeightNumber(20.0);
        animalWeightEntity2.setAnimalId(1);

        List<AnimalWeightEntity> weightEntityList = new ArrayList<>();
        weightEntityList.add(animalWeightEntity1);
        weightEntityList.add(animalWeightEntity2);

        when(weightRepository.findByAnimalId(any(int.class))).thenReturn(weightEntityList);

        // WHEN
        boolean deleted = weightServiceImplementation.deleteByAnimalId(1);

        // THEN
        assertTrue(deleted);

    }

}
