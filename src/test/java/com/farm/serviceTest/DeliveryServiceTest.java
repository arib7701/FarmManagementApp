package com.farm.serviceTest;

import com.farm.dao.AnimalDeliveryEntity;
import com.farm.exceptions.ApplicationException;
import com.farm.model.Animal;
import com.farm.model.AnimalType;
import com.farm.model.Delivery;
import com.farm.repository.DeliveryRepository;
import com.farm.service.implementation.AnimalServiceImplementation;
import com.farm.service.implementation.AnimalTypeServiceImplementation;
import com.farm.service.implementation.DeliveryServiceImplementation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DeliveryServiceTest {

    @InjectMocks
    DeliveryServiceImplementation deliveryServiceImplementation;

    @Mock
    DeliveryRepository deliveryRepository;

    @Mock
    AnimalServiceImplementation animalServiceImplementation;

    @Mock
    AnimalTypeServiceImplementation animalTypeServiceImplementation;

    @Test
    public void save_WhenParentSexInvalid_ExpectedNull() {

        // GIVEN
        Delivery delivery = new Delivery();
        delivery.setDate(LocalDate.now());
        delivery.setNumber(10);
        delivery.setMotherId(1);
        delivery.setFatherId(1);

        Animal mother = new Animal(1,"mom", "F", "A1", LocalDate.now().minusMonths(9), 1, null, null, "teen");
        when(animalServiceImplementation.findById(any(int.class))).thenReturn(mother);

        // WHEN
        try {
            deliveryServiceImplementation.save(delivery);
        }
        // THEN
        catch (ApplicationException e) {
            assertTrue("Error: the sex or age of the parents are invalid.".equals(e.getMessage()));
        }
    }

    @Test
    public void save_WhenParentAgeInvalid_ExpectedNull() {

        // GIVEN
        Delivery delivery = new Delivery();
        delivery.setDate(LocalDate.now());
        delivery.setNumber(10);
        delivery.setMotherId(1);
        delivery.setFatherId(2);

        Animal mother = new Animal(1,"mom", "F", "A1", LocalDate.now().minusMonths(9), 1, null, null, "teen");
        Animal father = new Animal(2, "dad", "M", "A2", LocalDate.now(), 1, null, null, "teen");
        AnimalType rabbit = new AnimalType(1, "rabbit", "", 6, 6, 6, 8);
        when(animalServiceImplementation.findById(1)).thenReturn(mother);
        when(animalServiceImplementation.findById(2)).thenReturn(father);
        when(animalTypeServiceImplementation.findById(1)).thenReturn(rabbit);

        // WHEN
        try {
            deliveryServiceImplementation.save(delivery);
        }
        // THEN
        catch (ApplicationException e) {
            assertTrue("Error: the sex or age of the parents are invalid.".equals(e.getMessage()));
        }
    }

    @Test
    public void save_WhenParentSexValid_ExpectedWeight() throws ApplicationException{

        // GIVEN
        Delivery delivery = new Delivery();
        delivery.setDate(LocalDate.now());
        delivery.setNumber(10);
        delivery.setMotherId(1);
        delivery.setFatherId(2);

        AnimalDeliveryEntity animalDeliveryEntity = new AnimalDeliveryEntity();
        animalDeliveryEntity.setDeliveryDate(java.sql.Date.valueOf((LocalDate.now())));
        animalDeliveryEntity.setDeliveryNumber(10);
        animalDeliveryEntity.setMotherId(1);
        animalDeliveryEntity.setFatherId(2);

        Animal mother = new Animal(1, "mom", "F", "A1", LocalDate.now(), 1, null, null, "teen");
        Animal father = new Animal(2, "dad", "M", "A2", LocalDate.now(), 1, null, null, "teen");
        when(animalServiceImplementation.findById(1)).thenReturn(mother);
        when(animalServiceImplementation.findById(2)).thenReturn(father);
        when(deliveryRepository.save(animalDeliveryEntity)).thenReturn(animalDeliveryEntity);

        // WHEN
        Delivery deliverySaved = deliveryServiceImplementation.save(delivery);
        // THEN
        assertTrue(deliverySaved != null && deliverySaved.getNumber() == 10);

    }

    @Test
    public void save_WhenParentDead_ExpectedNull() {

        // GIVEN
        Delivery delivery = new Delivery();
        delivery.setDate(LocalDate.now());
        delivery.setNumber(10);
        delivery.setMotherId(1);
        delivery.setFatherId(2);

        AnimalDeliveryEntity animalDeliveryEntity = new AnimalDeliveryEntity();
        animalDeliveryEntity.setDeliveryDate(java.sql.Date.valueOf((LocalDate.now())));
        animalDeliveryEntity.setDeliveryNumber(10);
        animalDeliveryEntity.setMotherId(1);
        animalDeliveryEntity.setFatherId(2);

        Animal mother = new Animal(1, "mom", "F", "A1", LocalDate.now(), 1, null, null, "teen");
        Animal father = new Animal(2, "dad", "M", "A2", LocalDate.now(), 1, null, null, "teen");
        father.setDeparture(LocalDate.now().plusDays(1));
        when(animalServiceImplementation.findById(1)).thenReturn(mother);
        when(animalServiceImplementation.findById(2)).thenReturn(father);

        // WHEN
        try {
            deliveryServiceImplementation.save(delivery);
        }
        // THEN
        catch (ApplicationException e) {
            assertTrue("Error: the parents are not alive or have been sold.".equals(e.getMessage()));
        }
    }


}
