package com.farm.dao;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "animal_delivery", schema = "farm", catalog = "")
public class AnimalDeliveryEntity {
    private int deliveryId;
    private Date deliveryDate;
    private int deliveryNumber;
    private Integer fatherId;
    private int motherId;
    private AnimalEntity animalByFatherId;
    private AnimalEntity animalByMotherId;

    @Id
    @Column(name = "delivery_id")
    public int getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(int deliveryId) {
        this.deliveryId = deliveryId;
    }

    @Basic
    @Column(name = "delivery_date")
    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    @Basic
    @Column(name = "delivery_number")
    public int getDeliveryNumber() {
        return deliveryNumber;
    }

    public void setDeliveryNumber(int deliveryNumber) {
        this.deliveryNumber = deliveryNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnimalDeliveryEntity that = (AnimalDeliveryEntity) o;
        return deliveryId == that.deliveryId &&
                deliveryNumber == that.deliveryNumber &&
                motherId == that.motherId &&
                Objects.equals(deliveryDate, that.deliveryDate) &&
                Objects.equals(fatherId, that.fatherId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deliveryId, deliveryDate, deliveryNumber, fatherId, motherId);
    }

    @ManyToOne
    @JoinColumn(name = "father_id", referencedColumnName = "animal_id")
    public AnimalEntity getAnimalByFatherId() {
        return animalByFatherId;
    }

    public void setAnimalByFatherId(AnimalEntity animalByFatherId) {
        this.animalByFatherId = animalByFatherId;
    }

    @ManyToOne
    @JoinColumn(name = "mother_id", referencedColumnName = "animal_id", nullable = false)
    public AnimalEntity getAnimalByMotherId() {
        return animalByMotherId;
    }

    public void setAnimalByMotherId(AnimalEntity animalByMotherId) {
        this.animalByMotherId = animalByMotherId;
    }
}
