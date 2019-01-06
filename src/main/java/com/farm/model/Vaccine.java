package com.farm.model;

import java.time.LocalDate;

public class Vaccine {

    private int id;
    private LocalDate date;
    private String type;
    private double quantity;
    private int animalId;

    public Vaccine() {
    }

    public Vaccine(int vaccineId, LocalDate date, String type, double quantity, int animalId) {
        this.id = vaccineId;
        this.date = date;
        this.type = type;
        this.quantity = quantity;
        this.animalId = animalId;
    }

    public Vaccine(LocalDate date, String type, double quantity, int animalId) {
        this.date = date;
        this.type = type;
        this.quantity = quantity;
        this.animalId = animalId;
    }

    public int getId() {
        return id;
    }

    public void setId(int vaccineId) {
        this.id = vaccineId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public int getAnimalId() {
        return animalId;
    }

    public void setAnimalId(int animalId) {
        this.animalId = animalId;
    }
}
