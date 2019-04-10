package com.farm.model;

import java.time.LocalDate;

public class Mating {

    private int id;
    private LocalDate date;
    private int fatherId;
    private int motherId;
    private LocalDate expectedDate;
    private boolean successfull;

    public Mating() {
    }

    public Mating(int id, LocalDate date, int fatherId, int motherId, LocalDate expectedDate, boolean successfull) {
        this.id = id;
        this.date = date;
        this.fatherId = fatherId;
        this.motherId = motherId;
        this.expectedDate = expectedDate;
        this.successfull = successfull;
    }

    public Mating(LocalDate date, int number, int fatherId, int motherId, LocalDate expectedDate, boolean successfull) {
        this.date = date;
        this.fatherId = fatherId;
        this.motherId = motherId;
        this.expectedDate = expectedDate;
        this.successfull = successfull;
    }

    public Mating(LocalDate date, int number) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getFatherId() {
        return fatherId;
    }

    public void setFatherId(int fatherId) {
        this.fatherId = fatherId;
    }

    public int getMotherId() {
        return motherId;
    }

    public void setMotherId(int motherId) {
        this.motherId = motherId;
    }

    public LocalDate getExpectedDate() { return expectedDate; }

    public void setExpectedDate(LocalDate expectedDate) { this.expectedDate = expectedDate; }

    public boolean isSuccessfull() { return successfull; }

    public void setSuccessfull(boolean successfull) { this.successfull = successfull; }
}
