package com.farm.model;

import java.time.LocalDate;

public class Weight {

    private int id;
    private LocalDate date;
    private double measure;
    private int animalId;

    public Weight() {
    }

    public Weight(int id, LocalDate date, double measure, int animalId) {
        this.id = id;
        this.date = date;
        this.measure = measure;
        this.animalId = animalId;
    }

    public Weight(LocalDate date, double measure, int animalId) {
        this.date = date;
        this.measure = measure;
        this.animalId = animalId;
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

    public double getMeasure() {
        return measure;
    }

    public void setMeasure(double measure) {
        this.measure = measure;
    }

    public int getAnimalId() {
        return animalId;
    }

    public void setAnimalId(int animalId) {
        this.animalId = animalId;
    }
}
