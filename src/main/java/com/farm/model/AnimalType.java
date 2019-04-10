package com.farm.model;

public class AnimalType {

    private int id;
    private String name;
    private String imageUrl;
    private int weeksGestation;
    private int monthsMaturity;
    private int minimumWeeksBetweenGestation;
    private int minimumWeeksSuckling;
    private double retirementYearsMale;
    private double retirementYearsFemale;

    public AnimalType() {
    }

    public AnimalType(int id, String name, String imageUrl, int gestation, int maturity, int inbetween, int suckling, double retirementYearsMale, double retirementYearsFemale) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.weeksGestation = gestation;
        this.monthsMaturity = maturity;
        this.minimumWeeksBetweenGestation = inbetween;
        this.minimumWeeksSuckling = suckling;
        this.retirementYearsFemale = retirementYearsFemale;
        this.retirementYearsMale = retirementYearsMale;
    }

    public AnimalType(String name, String imageUrl, int gestation, int maturity, int inbetween, int suckling, double retirementYearsMale, double retirementYearsFemale) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.weeksGestation = gestation;
        this.monthsMaturity = maturity;
        this.minimumWeeksBetweenGestation = inbetween;
        this.minimumWeeksSuckling = suckling;
        this.retirementYearsFemale = retirementYearsFemale;
        this.retirementYearsMale = retirementYearsMale;
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

    public double getRetirementYearsMale() { return retirementYearsMale; }

    public void setRetirementYearsMale(double retirementYearsMale) { this.retirementYearsMale = retirementYearsMale; }

    public double getRetirementYearsFemale() { return retirementYearsFemale; }

    public void setRetirementYearsFemale(double retirementYearsFemale) { this.retirementYearsFemale = retirementYearsFemale; }
}
