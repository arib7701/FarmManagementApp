package com.farm.service.implementation;

import com.farm.repository.VaccineRepository;
import com.farm.service.IVaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VaccineServiceImplementation implements IVaccineService {

    @Autowired
    private VaccineRepository vaccineRepository;
}
