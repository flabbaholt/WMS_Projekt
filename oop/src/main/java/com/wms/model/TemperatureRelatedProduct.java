package com.wms.model;

public class TemperatureRelatedProduct extends Product {
    // attributes 
    private String climateCategory;
    private String storageSpecial;

    // constructer 
    public TemperatureRelatedProduct(int id, int stellplatz_id, String productName, String manufacturer, String arrivalTime, String climateCategory, String storageSpecial) {
        super(id, stellplatz_id, productName, manufacturer, arrivalTime);
        this.climateCategory = climateCategory;
        this.storageSpecial = storageSpecial;
    }

    // getter -  methods
    public String getClimateCategory() {
        return this.climateCategory;
    }

    public String getStorageSpecial() {
        return this.storageSpecial;
    }

    // setter - methods
    public void setClimateCategory(String climateCategory) {
        this.climateCategory = climateCategory;
    }

    public void setStorageSpecial(String storageSpecial) {
        this.storageSpecial = storageSpecial;
    }
}
