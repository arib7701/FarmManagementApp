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
    private Date expectedDate;
    private boolean successfull;

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
    public int getFatherId() {
        return fatherId;
    }

    public void setFatherId(int fatherId) {
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

    @Basic
    @Column(name = "expected_date", nullable = false)
    public Date getExpectedDate() { return expectedDate; }

    public void setExpectedDate(Date expectedDate) { this.expectedDate = expectedDate; }

    @Basic
    @Column(name = "successfull", nullable = false)
    public boolean isSuccessfull() { return successfull; }

    public void setSuccessfull(boolean successfull) { this.successfull = successfull; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnimalMatingEntity that = (AnimalMatingEntity) o;
        return matingId == that.matingId &&
                motherId == that.motherId &&
                Objects.equals(matingDate, that.matingDate) &&
                Objects.equals(expectedDate, that.expectedDate) &&
                Objects.equals(successfull, that.successfull) &&
                fatherId == that.fatherId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(matingId, matingDate, fatherId, motherId, expectedDate, successfull);
    }
}
