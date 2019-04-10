package com.farm.utils;

import com.farm.exceptions.ApplicationException;
import com.farm.model.Animal;
import com.farm.model.AnimalType;
import com.farm.service.implementation.AnimalServiceImplementation;
import com.farm.service.implementation.AnimalTypeServiceImplementation;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public final class AnimalsUtils {

    public static boolean checkAnimalAlive(Object object, AnimalServiceImplementation animalServiceImplementation) throws IllegalAccessException, NoSuchFieldException {

        List<Animal> parents = getParents(object, animalServiceImplementation);

        boolean motherAlive = (parents.get(0).getDeath() == null && parents.get(0).getDeparture() == null);
        boolean fatherAlive = (parents.get(1).getDeath() == null && parents.get(1).getDeparture() == null);

        return (motherAlive && fatherAlive);
    }

    private static List<Animal> getParents(Object object, AnimalServiceImplementation animalServiceImplementation) throws IllegalAccessException, NoSuchFieldException {

        Field fatherIdField = object.getClass().getDeclaredField("father_id");
        Field motherIdField = object.getClass().getDeclaredField("mother_id");

        int fatherId = (int) fatherIdField.get(object);
        int motherId = (int) motherIdField.get(object);

        Animal mother = animalServiceImplementation.findById(motherId);
        Animal father = animalServiceImplementation.findById(fatherId);

        List<Animal> parents = new ArrayList<>();
        parents.add(mother);
        parents.add(father);

        return parents;
    }

    public static boolean checkCorrectSexAndAgeAndState(Object object, AnimalServiceImplementation animalServiceImplementation, AnimalTypeServiceImplementation animalTypeServiceImplementation) throws IllegalAccessException, NoSuchFieldException {

        List<Animal> parents = getParents(object, animalServiceImplementation);

        int typeId = parents.get(0).getType();
        AnimalType type = animalTypeServiceImplementation.findById(typeId);

        int monthsMaturity = type.getMonthsMaturity();
        LocalDate maturityDate = LocalDate.now().minusMonths(monthsMaturity);

        boolean correctSex = (parents.get(0).getSex().equals("F") && parents.get(1).getSex().equals("M"));
        boolean correctAge = (parents.get(0).getBirth().isBefore(maturityDate)) && (parents.get(1).getBirth().isBefore(maturityDate));
        boolean correctState = (parents.get(0).getState().equals("pregnant")) && (parents.get(1).getState().equals("supermale"));

        return (correctAge && correctSex && correctState);
    }
}
