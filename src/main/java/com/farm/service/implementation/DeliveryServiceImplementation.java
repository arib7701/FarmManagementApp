package com.farm.service.implementation;

import com.farm.repository.DeliveryRepository;
import com.farm.service.IDeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryServiceImplementation implements IDeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;
}
