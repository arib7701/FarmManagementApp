package com.farm.dao;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "animal_vaccine", schema = "farm", catalog = "")
public class AnimalVaccineEntity {
    private int vaccineId;
    private Date vaccineDate;
    private String vaccineType;
    private double vaccineQuantity;
    private int animalId;
    private AnimalEntity animalByAnimalId;

    @Id
    @Column(name = "vaccine_id")
    public int getVaccineId() {
        return vaccineId;
    }

    public void setVaccineId(int vaccineId) {
        this.vaccineId = vaccineId;
    }

    @Basic
    @Column(name = "vaccine_date")
    public Date getVaccineDate() {
        return vaccineDate;
    }

    public void setVaccineDate(Date vaccineDate) {
        this.vaccineDate = vaccineDate;
    }

    @Basic
    @Column(name = "vaccine_type")
    public String getVaccineType() {
        return vaccineType;
    }

    public void setVaccineType(String vaccineType) {
        this.vaccineType = vaccineType;
    }

    @Basic
    @Column(name = "vaccine_quantity")
    public double getVaccineQuantity() {
        return vaccineQuantity;
    }

    public void setVaccineQuantity(double vaccineQuantity) {
        this.vaccineQuantity = vaccineQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnimalVaccineEntity that = (AnimalVaccineEntity) o;
        return vaccineId == that.vaccineId &&
                Double.compare(that.vaccineQuantity, vaccineQuantity) == 0 &&
                animalId == that.animalId &&
                Objects.equals(vaccineDate, that.vaccineDate) &&
                Objects.equals(vaccineType, that.vaccineType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vaccineId, vaccineDate, vaccineType, vaccineQuantity, animalId);
    }

    @ManyToOne
    @JoinColumn(name = "animal_id", referencedColumnName = "animal_id", nullable = false)
    public AnimalEntity getAnimalByAnimalId() {
        return animalByAnimalId;
    }

    public void setAnimalByAnimalId(AnimalEntity animalByAnimalId) {
        this.animalByAnimalId = animalByAnimalId;
    }
}
