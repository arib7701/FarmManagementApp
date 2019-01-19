package com.farm.serviceTest;

import com.farm.dao.AnimalEntity;
import com.farm.exceptions.ApplicationException;
import com.farm.model.Animal;
import com.farm.repository.AnimalRepository;
import com.farm.repository.WeightRepository;
import com.farm.service.implementation.AnimalServiceImplementation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AnimalServiceTest {

    @InjectMocks
    AnimalServiceImplementation animalServiceImplementation;

    @Mock
    AnimalRepository animalRepository;


    @Test
    public void save_WhenDeathCauseInvalid_ExpectedNull() {

        // GIVEN
        Animal animal = new Animal();
        animal.setId(1);
        animal.setName("Roger");
        animal.setDeathCause("Acid");

        // WHEN
        try {
            animalServiceImplementation.save(animal);
        }
        // THEN
        catch (ApplicationException e) {
            assertTrue("Error: Some of the dates are invalid".equals(e.getMessage()));
        }
    }

    @Test
    public void save_WhenDatesAreInvalid_ExpectedNull() {

        // GIVEN
        Animal animal = new Animal();
        animal.setId(1);
        animal.setName("Roger");
        animal.setDeath(LocalDate.now().minusDays(1));
        animal.setBirth(LocalDate.now());

        // WHEN
        try {
            animalServiceImplementation.save(animal);
        }
        // THEN
        catch (ApplicationException e) {
            assertTrue("Error: Some of the dates are invalid".equals(e.getMessage()));
        }
    }

    @Test
    public void save_WhenAnimalValid_ExpectedAnimal() throws ApplicationException {

        // GIVEN
        Animal animal = new Animal();
        animal.setName("Roger");
        animal.setType(1);
        animal.setBirth(LocalDate.now().minusDays(10));
        animal.setArrival(LocalDate.now().minusDays(5));
        animal.setDeath(LocalDate.now());
        animal.setDeparture(LocalDate.now());
        animal.setDeathCause("Acid");

        AnimalEntity animalEntity = new AnimalEntity();
        animalEntity.setAnimalName("Roger");
        animalEntity.setAnimalType(1);
        animalEntity.setDateBirth(java.sql.Date.valueOf(LocalDate.now().minusDays(10)));
        animalEntity.setDateArrival(java.sql.Date.valueOf(LocalDate.now().minusDays(5)));
        animalEntity.setDateDeath(java.sql.Date.valueOf(LocalDate.now()));
        animalEntity.setDateDeparture(java.sql.Date.valueOf(LocalDate.now()));
        animalEntity.setDeathCause("Acid");

        when(animalRepository.save(animalEntity)).thenReturn(animalEntity);

        // WHEN
        Animal animalSaved = animalServiceImplementation.save(animal);
        // THEN
        assertTrue(animalSaved != null && animalSaved.getName().equals("Roger") && animalSaved.getDeathCause().equals("Acid"));
    }

    @Test
    public void update_WhenAnimalNotExists_ExpectedNull() {

        // GIVEN
        Animal animal = new Animal();
        animal.setId(1);
        animal.setName("Roger");
        animal.setType(1);

        when(animalRepository.findByAnimalId(any(int.class))).thenReturn(null);

        // WHEN
        try {
            animalServiceImplementation.update(1, animal);
        }
        // THEN
        catch (ApplicationException e) {
            assertTrue("Error: Animal does not exist".equals(e.getMessage()));
        }
    }

    @Test
    public void update_WhenDeathCauseInvalid_ExpectedNull() {

        // GIVEN
        Animal animal = new Animal();
        animal.setId(1);
        animal.setName("Roger");
        animal.setDeathCause("Acid");

        AnimalEntity animalEntity = new AnimalEntity();
        animalEntity.setAnimalName("Roger");
        animalEntity.setDeathCause("Acid");

        when(animalRepository.findByAnimalId(any(int.class))).thenReturn(animalEntity);

        // WHEN
        try {
            animalServiceImplementation.update(1, animal);
        }
        // THEN
        catch (ApplicationException e) {
            assertTrue("Error: Some of the dates are invalid".equals(e.getMessage()));
        }
    }

    @Test
    public void update_WhenDatesAreInvalid_ExpectedNull() {

        // GIVEN
        Animal animal = new Animal();
        animal.setId(1);
        animal.setName("Roger");
        animal.setDeparture(LocalDate.now().minusDays(1));
        animal.setArrival(LocalDate.now());

        AnimalEntity animalEntity = new AnimalEntity();
        animalEntity.setAnimalName("Roger");
        animalEntity.setDateArrival(java.sql.Date.valueOf(LocalDate.now().minusDays(1)));
        animalEntity.setDateDeparture(java.sql.Date.valueOf(LocalDate.now()));

        when(animalRepository.findByAnimalId(any(int.class))).thenReturn(animalEntity);

        // WHEN
        try {
            animalServiceImplementation.update(1, animal);
        }
        // THEN
        catch (ApplicationException e) {
            assertTrue("Error: Some of the dates are invalid".equals(e.getMessage()));
        }
    }

    @Test
    public void update_WhenAnimalValid_ExpectedAnimal() throws ApplicationException {

        // GIVEN
        Animal animal = new Animal();
        animal.setName("Roger");
        animal.setBirth(LocalDate.now().minusDays(10));
        animal.setArrival(LocalDate.now().minusDays(5));
        animal.setDeath(LocalDate.now());
        animal.setDeparture(LocalDate.now());
        animal.setDeathCause("Acid");

        AnimalEntity animalEntity = new AnimalEntity();
        animalEntity.setAnimalName("Roger");
        animalEntity.setDateBirth(java.sql.Date.valueOf(LocalDate.now().minusDays(10)));
        animalEntity.setDateArrival(java.sql.Date.valueOf(LocalDate.now().minusDays(5)));
        animalEntity.setDateDeath(java.sql.Date.valueOf(LocalDate.now()));
        animalEntity.setDateDeparture(java.sql.Date.valueOf(LocalDate.now()));
        animalEntity.setDeathCause("Acid");

        when(animalRepository.save(animalEntity)).thenReturn(animalEntity);

        // WHEN
        Animal animalUpdated = animalServiceImplementation.save(animal);
        // THEN
        assertTrue(animalUpdated != null && animalUpdated.getName().equals("Roger") && animalUpdated.getDeathCause().equals("Acid"));
    }


}
