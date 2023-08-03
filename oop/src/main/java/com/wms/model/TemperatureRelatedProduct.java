package com.wms.model;

public class TemperatureRelatedProduct extends Product {
    // attributes 
    private String climateCategory;
    private String storageSpecial;

    // constructer 

    public TemperatureRelatedProduct(Integer productID, String productNumber, String stellplatzID, String productName, String manufacturer, String arrivalTime, String climateCategory, String storageSpecial) {
        super(productID, productNumber, stellplatzID, productName, manufacturer, arrivalTime);
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
