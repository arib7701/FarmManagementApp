package com.farm.dao;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(name = "animal_delivery", schema = "farm", catalog = "")
public class AnimalDeliveryEntity {
    private int deliveryId;
    private Date deliveryDate;
    private int deliveryNumber;
    private Integer fatherId;
    private int motherId;

    @Id
    @Column(name = "delivery_id", nullable = false)
    public int getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(int deliveryId) {
        this.deliveryId = deliveryId;
    }

    @Basic
    @Column(name = "delivery_date", nullable = false)
    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    @Basic
    @Column(name = "delivery_number", nullable = false)
    public int getDeliveryNumber() {
        return deliveryNumber;
    }

    public void setDeliveryNumber(int deliveryNumber) {
        this.deliveryNumber = deliveryNumber;
    }

    @Basic
    @Column(name = "father_id", nullable = true)
    public Integer getFatherId() {
        return fatherId;
    }

    public void setFatherId(Integer fatherId) {
        this.fatherId = fatherId;
    }

    @Basic
    @Column(name = "mother_id", nullable = false)
    public int getMotherId() {
        return motherId;
    }

    public void setMotherId(int motherId) {
        this.motherId = motherId;
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
}
