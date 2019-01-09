package com.farm.dao;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "animal", schema = "farm", catalog = "")
public class AnimalEntity {
    private int animalId;
    private String animalName;
    private String animalSex;
    private Date dateBirth;
    private Date dateDeath;
    private Date dateArrival;
    private Date dateDeparture;
    private int animalType;
    private Integer fatherId;
    private Integer motherId;
    private Byte isResearch;
    private String barn;

    @Id
    @Column(name = "animal_id", nullable = false)
    public int getAnimalId() {
        return animalId;
    }

    public void setAnimalId(int animalId) {
        this.animalId = animalId;
    }

    @Basic
    @Column(name = "animal_name", nullable = false, length = 25)
    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    @Basic
    @Column(name = "animal_sex", nullable = true, length = 1)
    public String getAnimalSex() {
        return animalSex;
    }

    public void setAnimalSex(String animalSex) {
        this.animalSex = animalSex;
    }

    @Basic
    @Column(name = "date_birth", nullable = true)
    public Date getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(Date dateBirth) {
        this.dateBirth = dateBirth;
    }

    @Basic
    @Column(name = "date_death", nullable = true)
    public Date getDateDeath() {
        return dateDeath;
    }

    public void setDateDeath(Date dateDeath) {
        this.dateDeath = dateDeath;
    }

    @Basic
    @Column(name = "date_arrival", nullable = true)
    public Date getDateArrival() {
        return dateArrival;
    }

    public void setDateArrival(Date dateArrival) {
        this.dateArrival = dateArrival;
    }

    @Basic
    @Column(name = "date_departure", nullable = true)
    public Date getDateDeparture() {
        return dateDeparture;
    }

    public void setDateDeparture(Date dateDeparture) {
        this.dateDeparture = dateDeparture;
    }

    @Basic
    @Column(name = "animal_type", nullable = false)
    public int getAnimalType() {
        return animalType;
    }

    public void setAnimalType(int animalType) {
        this.animalType = animalType;
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
    @Column(name = "mother_id", nullable = true)
    public Integer getMotherId() {
        return motherId;
    }

    public void setMotherId(Integer motherId) {
        this.motherId = motherId;
    }

    @Basic
    @Column(name = "is_research", nullable = true)
    public Byte getIsResearch() {
        return isResearch;
    }

    public void setIsResearch(Byte isResearch) {
        this.isResearch = isResearch;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnimalEntity that = (AnimalEntity) o;
        return animalId == that.animalId &&
                animalType == that.animalType &&
                Objects.equals(animalName, that.animalName) &&
                Objects.equals(animalSex, that.animalSex) &&
                Objects.equals(dateBirth, that.dateBirth) &&
                Objects.equals(dateDeath, that.dateDeath) &&
                Objects.equals(dateArrival, that.dateArrival) &&
                Objects.equals(dateDeparture, that.dateDeparture) &&
                Objects.equals(fatherId, that.fatherId) &&
                Objects.equals(motherId, that.motherId) &&
                Objects.equals(isResearch, that.isResearch);
    }

    @Override
    public int hashCode() {
        return Objects.hash(animalId, animalName, animalSex, dateBirth, dateDeath, dateArrival, dateDeparture, animalType, fatherId, motherId, isResearch);
    }

    @Basic
    @Column(name = "barn", nullable = true, length = 45)
    public String getBarn() {
        return barn;
    }

    public void setBarn(String barn) {
        this.barn = barn;
    }
}
