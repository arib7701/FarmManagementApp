package com.farm.dao;

import javax.persistence.*;
import java.util.Objects;

@SuppressWarnings("ALL")
@Entity
@Table(name = "animal_type", schema = "farm", catalog = "")
public class AnimalTypeEntity {
    private int typeId;
    private String typeName;
    private String typeImg;
    private Integer weeksGestation;
    private Integer monthsMaturity;
    private Integer weeksBetweenGestation;
    private Integer weeksSuckling;
    private double retirementYearsMale;
    private double retirementYearsFemale;

    @Id
    @Column(name = "type_id", nullable = false)
    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    @Basic
    @Column(name = "type_name", nullable = false, length = 25)
    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Basic
    @Column(name = "type_img", nullable = true, length = 255)
    public String getTypeImg() {
        return typeImg;
    }

    public void setTypeImg(String typeImg) {
        this.typeImg = typeImg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnimalTypeEntity that = (AnimalTypeEntity) o;
        return typeId == that.typeId &&
                Objects.equals(typeName, that.typeName) &&
                Objects.equals(typeImg, that.typeImg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeId, typeName, typeImg);
    }

    @Basic
    @Column(name = "weeks_gestation", nullable = true)
    public Integer getWeeksGestation() {
        return weeksGestation;
    }

    public void setWeeksGestation(Integer weeksGestation) {
        this.weeksGestation = weeksGestation;
    }

    @Basic
    @Column(name = "months_maturity", nullable = true)
    public Integer getMonthsMaturity() {
        return monthsMaturity;
    }

    public void setMonthsMaturity(Integer monthsMaturity) {
        this.monthsMaturity = monthsMaturity;
    }

    @Basic
    @Column(name = "weeks_between_gestation", nullable = true)
    public Integer getWeeksBetweenGestation() {
        return weeksBetweenGestation;
    }

    public void setWeeksBetweenGestation(Integer weeksBetweenGestation) {
        this.weeksBetweenGestation = weeksBetweenGestation;
    }

    @Basic
    @Column(name = "weeks_suckling", nullable = true)
    public Integer getWeeksSuckling() {
        return weeksSuckling;
    }

    public void setWeeksSuckling(Integer weeksSuckling) {
        this.weeksSuckling = weeksSuckling;
    }

    @Basic
    @Column(name = "retirement_years_male", nullable = true)
    public double getRetirementYearsMale() { return retirementYearsMale; }

    public void setRetirementYearsMale(double retirementYearsMale) { this.retirementYearsMale = retirementYearsMale; }

    @Basic
    @Column(name = "retirement_years_female", nullable = true)
    public double getRetirementYearsFemale() { return retirementYearsFemale; }

    public void setRetirementYearsFemale(double getRetirementYearsFemale) { this.retirementYearsFemale = getRetirementYearsFemale; }
}
