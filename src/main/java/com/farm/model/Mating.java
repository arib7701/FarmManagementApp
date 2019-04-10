package com.farm.model;

import java.time.LocalDate;

public class Mating {

    private int id;
    private LocalDate date;
    private int fatherId;
    private int motherId;

    public Mating() {
    }

    public Mating(int id, LocalDate date, int fatherId, int motherId) {
        this.id = id;
        this.date = date;
        this.fatherId = fatherId;
        this.motherId = motherId;
    }

    public Mating(LocalDate date, int number, int fatherId, int motherId) {
        this.date = date;
        this.fatherId = fatherId;
        this.motherId = motherId;
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
}
