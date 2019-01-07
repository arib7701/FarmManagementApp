package com.farm.repository;

import com.farm.dao.AnimalDeliveryEntity;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.List;

public interface DeliveryRepository extends CrudRepository<AnimalDeliveryEntity, Integer> {

    List<AnimalDeliveryEntity> findByFatherId(int id);
    List<AnimalDeliveryEntity> findByMotherId(int id);
    List<AnimalDeliveryEntity> findByDeliveryDate(Date date);
}
