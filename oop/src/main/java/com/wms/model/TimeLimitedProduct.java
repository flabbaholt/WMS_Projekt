package com.wms.model;

public class TimeLimitedProduct extends Product {
    
    // attributes
    private String expiryDate;
    private String status;

    // constructer
    public TimeLimitedProduct(int id, int stellplatz_id, String productName, String manufacturer, String arrivalTime, String expiryDate, String status) {
        super(id, stellplatz_id, productName, manufacturer, arrivalTime);
        this.expiryDate = expiryDate;
        this.status = status;
    }

    // getter - methods
    public String getExpiryDate() {
        return this.expiryDate;
    }
    
    public String getStatus() {
        return this.status;
    }
    
    // setter - methods
    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }


}
