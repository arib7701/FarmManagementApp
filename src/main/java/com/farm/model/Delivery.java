package com.farm.model;

import java.time.LocalDate;

public class Delivery {

    private int id;
    private LocalDate date;
    private int number;
    private int fatherId;
    private int motherId;
    private Animal father;
    private Animal mother;

    public Delivery() {
    }

    public Delivery(int id, LocalDate date, int number, int fatherId, int motherId, Animal father, Animal mother) {
        this.id = id;
        this.date = date;
        this.number = number;
        this.fatherId = fatherId;
        this.motherId = motherId;
        this.father = father;
        this.mother = mother;
    }

    public Delivery(LocalDate date, int number, int fatherId, int motherId, Animal father, Animal mother) {
        this.date = date;
        this.number = number;
        this.fatherId = fatherId;
        this.motherId = motherId;
        this.father = father;
        this.mother = mother;
    }

    public Delivery(LocalDate date, int number, int fatherId, int motherId) {
        this.date = date;
        this.number = number;
        this.fatherId = fatherId;
        this.motherId = motherId;
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

    public Animal getFather() {
        return father;
    }

    public void setFather(Animal father) {
        this.father = father;
    }

    public Animal getMother() {
        return mother;
    }

    public void setMother(Animal mother) {
        this.mother = mother;
    }
}
