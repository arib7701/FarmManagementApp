package com.farm.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Animal {

    private int id;
    private String name;
    private String sex;
    private String barn;
    private LocalDate birth;
    private LocalDate death;
    private LocalDate arrival;
    private LocalDate departure;
    private int type;
    private Integer fatherId;
    private Integer motherId;
    private boolean isResearch;
    private List<Weight> weights;
    private List<Vaccine> vaccines;
    private List<Delivery> deliveries;
    private String deathCause;
    private String state;

    public Animal() {
    }

    public Animal(int id, String name, String sex, String barn, LocalDate birth, int type, Integer fatherId, Integer motherId, String state) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.barn = barn;
        this.birth = birth;
        this.death = null;
        this.arrival = null;
        this.departure = null;
        this.type = type;
        this.fatherId = fatherId;
        this.motherId = motherId;
        this.isResearch = false;
        this.state = state;
        this.weights = new ArrayList<>();
        this.vaccines = new ArrayList<>();
        this.deliveries = new ArrayList<>();
    }

    public Animal(String name, String sex, String barn, LocalDate birth, int type, Integer fatherId, Integer motherId, String state) {
        this.name = name;
        this.sex = sex;
        this.barn = barn;
        this.birth = birth;
        this.death = null;
        this.arrival = null;
        this.departure = null;
        this.type = type;
        this.fatherId = fatherId;
        this.motherId = motherId;
        this.isResearch = false;
        this.state = state;
        this.weights = new ArrayList<>();
        this.vaccines = new ArrayList<>();
        this.deliveries = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBarn() {
        return barn;
    }

    public void setBarn(String barn) {
        this.barn = barn;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public LocalDate getDeath() {
        return death;
    }

    public void setDeath(LocalDate death) {
        this.death = death;
    }

    public LocalDate getArrival() {
        return arrival;
    }

    public void setArrival(LocalDate arrival) {
        this.arrival = arrival;
    }

    public LocalDate getDeparture() {
        return departure;
    }

    public void setDeparture(LocalDate departure) {
        this.departure = departure;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Integer getFatherId() {
        return fatherId;
    }

    public void setFatherId(Integer fatherId) {
        this.fatherId = fatherId;
    }

    public Integer getMotherId() {
        return motherId;
    }

    public void setMotherId(Integer motherId) {
        this.motherId = motherId;
    }

    public boolean getIsResearch() {
        return isResearch;
    }

    public void setIsResearch(boolean research) {
        isResearch = research;
    }

    public List<Weight> getWeights() {
        return weights;
    }

    public void setWeights(List<Weight> weights) {
        this.weights = weights;
    }

    public List<Vaccine> getVaccines() {
        return vaccines;
    }

    public void setVaccines(List<Vaccine> vaccines) {
        this.vaccines = vaccines;
    }

    public List<Delivery> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(List<Delivery> deliveries) {
        this.deliveries = deliveries;
    }

    public String getDeathCause() { return deathCause; }

    public void setDeathCause(String deathCause) { this.deathCause = deathCause; }

    public String getState() { return state; }

    public void setState(String state) { this.state = state; }
}
