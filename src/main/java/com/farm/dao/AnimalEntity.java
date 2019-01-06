package com.farm.dao;

import jdk.nashorn.internal.ir.annotations.Ignore;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
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
    private AnimalTypeEntity animalTypeByAnimalType;
    private AnimalEntity animalByFatherId;
    private AnimalEntity animalsByAnimalFatherId;
    private AnimalEntity animalByMotherId;
    private AnimalEntity animalsByAnimalMotherId;
    private Collection<AnimalDeliveryEntity> animalDeliveriesByAnimalFatherId;
    private Collection<AnimalDeliveryEntity> animalDeliveriesByAnimalMotherId;
    private Collection<AnimalVaccineEntity> animalVaccinesByAnimalId;
    private Collection<AnimalWeightEntity> animalWeightsByAnimalId;

    @Id
    @Column(name = "animal_id")
    public int getAnimalId() {
        return animalId;
    }

    public void setAnimalId(int animalId) {
        this.animalId = animalId;
    }

    @Basic
    @Column(name = "animal_name")
    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    @Basic
    @Column(name = "animal_sex")
    public String getAnimalSex() {
        return animalSex;
    }

    public void setAnimalSex(String animalSex) {
        this.animalSex = animalSex;
    }

    @Basic
    @Column(name = "date_birth")
    public Date getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(Date dateBirth) {
        this.dateBirth = dateBirth;
    }

    @Basic
    @Column(name = "date_death")
    public Date getDateDeath() {
        return dateDeath;
    }

    public void setDateDeath(Date dateDeath) {
        this.dateDeath = dateDeath;
    }

    @Basic
    @Column(name = "date_arrival")
    public Date getDateArrival() {
        return dateArrival;
    }

    public void setDateArrival(Date dateArrival) {
        this.dateArrival = dateArrival;
    }

    @Basic
    @Column(name = "date_departure")
    public Date getDateDeparture() {
        return dateDeparture;
    }

    public void setDateDeparture(Date dateDeparture) {
        this.dateDeparture = dateDeparture;
    }

    @Ignore
    public int getAnimalType() {
        return animalType;
    }

    @Ignore
    public void setAnimalType(int animalType) {
        this.animalType = animalType;
    }

    @Ignore
    public Integer getFatherId() {
        return fatherId;
    }

    public void setFatherId(Integer fatherId) {
        this.fatherId = fatherId;
    }

    public Integer getMotherId() {
        return motherId;
    }

    public void setMotherId(Integer motherId) {
        this.motherId = motherId;
    }

    @Basic
    @Column(name = "is_research")
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

    @ManyToOne
    @JoinColumn(name = "animal_type", referencedColumnName = "type_id", nullable = false)
    public AnimalTypeEntity getAnimalTypeByAnimalType() {
        return animalTypeByAnimalType;
    }

    public void setAnimalTypeByAnimalType(AnimalTypeEntity animalTypeByAnimalType) {
        this.animalTypeByAnimalType = animalTypeByAnimalType;
    }

    @OneToOne
    @JoinColumn(name = "father_id", referencedColumnName = "animal_id")
    public AnimalEntity getAnimalByFatherId() {
        return animalByFatherId;
    }

    public void setAnimalByFatherId(AnimalEntity animalByFatherId) {
        this.animalByFatherId = animalByFatherId;
    }

    @OneToOne(mappedBy = "animalByFatherId")
    public AnimalEntity getAnimalsByAnimalFatherId() {
        return animalsByAnimalFatherId;
    }

    public void setAnimalsByAnimalFatherId(AnimalEntity animalsByAnimalFatherId) {
        this.animalsByAnimalFatherId = animalsByAnimalFatherId;
    }

    @OneToOne
    @JoinColumn(name = "mother_id", referencedColumnName = "animal_id")
    public AnimalEntity getAnimalByMotherId() {
        return animalByMotherId;
    }

    public void setAnimalByMotherId(AnimalEntity animalByMotherId) {
        this.animalByMotherId = animalByMotherId;
    }

    @OneToOne(mappedBy = "animalByMotherId")
    public AnimalEntity getAnimalsByAnimalMotherId() {
        return animalsByAnimalMotherId;
    }

    public void setAnimalsByAnimalMotherId(AnimalEntity animalsByAnimalMotherId) {
        this.animalsByAnimalMotherId = animalsByAnimalMotherId;
    }

    @OneToMany(mappedBy = "animalByFatherId")
    public Collection<AnimalDeliveryEntity> getAnimalDeliveriesByAnimalFatherId() {
        return animalDeliveriesByAnimalFatherId;
    }

    public void setAnimalDeliveriesByAnimalFatherId(Collection<AnimalDeliveryEntity> animalDeliveriesByAnimalFatherId) {
        this.animalDeliveriesByAnimalFatherId = animalDeliveriesByAnimalFatherId;
    }

    @OneToMany(mappedBy = "animalByMotherId")
    public Collection<AnimalDeliveryEntity> getAnimalDeliveriesByAnimalMotherId() {
        return animalDeliveriesByAnimalMotherId;
    }

    public void setAnimalDeliveriesByAnimalMotherId(Collection<AnimalDeliveryEntity> animalDeliveriesByAnimalMotherId) {
        this.animalDeliveriesByAnimalMotherId = animalDeliveriesByAnimalMotherId;
    }

    @OneToMany(mappedBy = "animalByAnimalId")
    public Collection<AnimalVaccineEntity> getAnimalVaccinesByAnimalId() {
        return animalVaccinesByAnimalId;
    }

    public void setAnimalVaccinesByAnimalId(Collection<AnimalVaccineEntity> animalVaccinesByAnimalId) {
        this.animalVaccinesByAnimalId = animalVaccinesByAnimalId;
    }

    @OneToMany(mappedBy = "animalByAnimalId")
    public Collection<AnimalWeightEntity> getAnimalWeightsByAnimalId() {
        return animalWeightsByAnimalId;
    }

    public void setAnimalWeightsByAnimalId(Collection<AnimalWeightEntity> animalWeightsByAnimalId) {
        this.animalWeightsByAnimalId = animalWeightsByAnimalId;
    }
}
