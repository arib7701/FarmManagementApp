package com.farm.dao;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@SuppressWarnings("ALL")
@Entity
@Table(name = "animal_mating", schema = "farm", catalog = "")
public class AnimalMatingEntity {
    private int matingId;
    private Date matingDate;
    private int fatherId;
    private int motherId;

    @Id
    @Column(name = "mating_id", nullable = false)
    public int getMatingId() {
        return matingId;
    }

    public void setMatingId(int matingId) {
        this.matingId = matingId;
    }

    @Basic
    @Column(name = "mating_date", nullable = false)
    public Date getMatingDate() {
        return matingDate;
    }

    public void setMatingDate(Date matingDate) {
        this.matingDate = matingDate;
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
        AnimalMatingEntity that = (AnimalMatingEntity) o;
        return matingId == that.matingId &&
                motherId == that.motherId &&
                Objects.equals(matingDate, that.matingDate) &&
                Objects.equals(fatherId, that.fatherId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matingId, matingDate, fatherId, motherId);
    }
}
