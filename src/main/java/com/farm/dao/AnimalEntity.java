package com.farm.dao;

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
    private AnimalEntity animalsByAnimalId;
    private AnimalEntity animalByMotherId;
    private AnimalEntity animalsByAnimalId_0;
    private Collection<AnimalDeliveryEntity> animalDeliveriesByAnimalId;
    private Collection<AnimalDeliveryEntity> animalDeliveriesByAnimalId_0;
    private Collection<AnimalVaccineEntity> animalVaccinesByAnimalId;
    private Collection<AnimalWeightEntity> animalWeightsByAnimalId;

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
    public AnimalEntity getAnimalsByAnimalId() {
        return animalsByAnimalId;
    }

    public void setAnimalsByAnimalId(AnimalEntity animalsByAnimalId) {
        this.animalsByAnimalId = animalsByAnimalId;
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
    public AnimalEntity getAnimalsByAnimalId_0() {
        return animalsByAnimalId_0;
    }

    public void setAnimalsByAnimalId_0(AnimalEntity animalsByAnimalId_0) {
        this.animalsByAnimalId_0 = animalsByAnimalId_0;
    }

    @OneToMany(mappedBy = "animalByFatherId")
    public Collection<AnimalDeliveryEntity> getAnimalDeliveriesByAnimalId() {
        return animalDeliveriesByAnimalId;
    }

    public void setAnimalDeliveriesByAnimalId(Collection<AnimalDeliveryEntity> animalDeliveriesByAnimalId) {
        this.animalDeliveriesByAnimalId = animalDeliveriesByAnimalId;
    }

    @OneToMany(mappedBy = "animalByMotherId")
    public Collection<AnimalDeliveryEntity> getAnimalDeliveriesByAnimalId_0() {
        return animalDeliveriesByAnimalId_0;
    }

    public void setAnimalDeliveriesByAnimalId_0(Collection<AnimalDeliveryEntity> animalDeliveriesByAnimalId_0) {
        this.animalDeliveriesByAnimalId_0 = animalDeliveriesByAnimalId_0;
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
