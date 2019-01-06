package com.farm.model;

import java.time.LocalDate;

public class Delivery {

    private int id;
    private LocalDate date;
    private int number;
    private int fatherId;
    private int motherId;

    public Delivery() {
    }

    public Delivery(int id, LocalDate date, int number, int fatherId, int motherId) {
        this.id = id;
        this.date = date;
        this.number = number;
        this.fatherId = fatherId;
        this.motherId = motherId;
    }

    public Delivery(LocalDate date, int number, int fatherId, int motherId) {
        this.date = date;
        this.number = number;
        this.fatherId = fatherId;
        this.motherId = motherId;
    }

    public Delivery(LocalDate date, int number) {
        this.date = date;
        this.number = number;
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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
