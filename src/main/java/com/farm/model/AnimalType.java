package com.farm.model;

public class AnimalType {

    private int id;
    private String name;

    public AnimalType() {
    }

    public AnimalType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public AnimalType(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
