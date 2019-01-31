package com.farm.model;

public class AnimalType {

    private int id;
    private String name;
    private String imageUrl;
    private int weeksGestation;
    private int monthsMaturity;
    private int minimumWeeksBetweenGestation;
    private int minimumWeeksSuckling;

    public AnimalType() {
    }

    public AnimalType(int id, String name, String imageUrl, int gestation, int maturity, int inbetween, int suckling) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.weeksGestation = gestation;
        this.monthsMaturity = maturity;
        this.minimumWeeksBetweenGestation = inbetween;
        this.minimumWeeksSuckling = suckling;
    }

    public AnimalType(String name, String imageUrl, int gestation, int maturity, int inbetween, int suckling) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.weeksGestation = gestation;
        this.monthsMaturity = maturity;
        this.minimumWeeksBetweenGestation = inbetween;
        this.minimumWeeksSuckling = suckling;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getWeeksGestation() { return weeksGestation; }

    public void setWeeksGestation(int weeksGestation) { this.weeksGestation = weeksGestation; }

    public int getMonthsMaturity() { return monthsMaturity; }

    public void setMonthsMaturity(int monthsMaturity) { this.monthsMaturity = monthsMaturity; }

    public int getMinimumWeeksBetweenGestation() { return minimumWeeksBetweenGestation; }

    public void setMinimumWeeksBetweenGestation(int minimumMonthsBetweenGestation) { this.minimumWeeksBetweenGestation = minimumMonthsBetweenGestation; }

    public int getMinimumWeeksSuckling() { return minimumWeeksSuckling; }

    public void setMinimumWeeksSuckling(int minimumWeeksSuckling) { this.minimumWeeksSuckling = minimumWeeksSuckling; }
}
