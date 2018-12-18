package com.farm.repository;

import com.farm.dao.AnimalDeliveryEntity;
import org.springframework.data.repository.CrudRepository;

public interface DeliveryRepository extends CrudRepository<AnimalDeliveryEntity, Integer> {
}
