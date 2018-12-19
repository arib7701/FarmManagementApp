package com.farm.dao;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "animal_weight", schema = "farm", catalog = "")
public class AnimalWeightEntity {
    private int weightId;
    private Date weightDate;
    private double weightNumber;
    private int animalId;
    private AnimalEntity animalByAnimalId;

    @Id
    @Column(name = "weight_id")
    public int getWeightId() {
        return weightId;
    }

    public void setWeightId(int weightId) {
        this.weightId = weightId;
    }

    @Basic
    @Column(name = "weight_date")
    public Date getWeightDate() {
        return weightDate;
    }

    public void setWeightDate(Date weightDate) {
        this.weightDate = weightDate;
    }

    @Basic
    @Column(name = "weight_number")
    public double getWeightNumber() {
        return weightNumber;
    }

    public void setWeightNumber(double weightNumber) {
        this.weightNumber = weightNumber;
    }

    @Basic
    @Column(name = "animal_id")
    public int getAnimalId() {
        return animalId;
    }

    public void setAnimalId(int animalId) {
        this.animalId = animalId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnimalWeightEntity that = (AnimalWeightEntity) o;
        return weightId == that.weightId &&
                Double.compare(that.weightNumber, weightNumber) == 0 &&
                animalId == that.animalId &&
                Objects.equals(weightDate, that.weightDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(weightId, weightDate, weightNumber, animalId);
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
