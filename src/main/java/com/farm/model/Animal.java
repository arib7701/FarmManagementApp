package com.farm.model;

import java.time.LocalDate;
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
    private String type;
    private Integer fatherId;
    private Integer motherId;
    private Boolean isResearch;
    private List<Weight> weights;
    private List<Vaccine> vaccines;
    private List<Delivery> deliveries;

    public Animal() {
    }

    public Animal(int id, String name, String sex, String barn, LocalDate birth, String type, Integer fatherId, Integer motherId) {
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
        this.isResearch = null;
        this.weights = null;
        this.vaccines = null;
        this.deliveries = null;
    }

    public Animal(String name, String sex, String barn, LocalDate birth, String type, Integer fatherId, Integer motherId) {
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
        this.isResearch = null;
        this.weights = null;
        this.vaccines = null;
        this.deliveries = null;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
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

    public Boolean getResearch() {
        return isResearch;
    }

    public void setResearch(Boolean research) {
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
}
