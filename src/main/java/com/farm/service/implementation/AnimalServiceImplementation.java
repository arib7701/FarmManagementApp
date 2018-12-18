package com.farm.service.implementation;

import com.farm.repository.AnimalRepository;
import com.farm.service.IAnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnimalServiceImplementation implements IAnimalService {

    @Autowired
    private AnimalRepository animalRepository;
}
