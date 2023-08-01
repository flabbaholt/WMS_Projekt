package com.wms.model;

import com.wms.model.interfaces.Displayable;

public class Product implements Displayable{
    // attributes
    private int id;
    private int stellplatz_id;
    private String productName;
    private String manufacturer;
    private String arrivalTime;

    //Produktkurzberschreibung 
    
    // constructer
    public Product(int id, int stellplatz_id, String productName, String manufacturer, String arrivalTime) {
        this.id = id;
        this.stellplatz_id = stellplatz_id;
        this.productName = productName;
        this.manufacturer = manufacturer;
        this.arrivalTime = arrivalTime;
    }

    // getter - methods
    public int getId() {
        return this.id;
    }

    public int getStellplatz_id() {
        return this.stellplatz_id;
    }
    
    public String getProductName() {
        return this.productName;
    }

    public String getManufacturer() {
        return this.manufacturer;
    }
    
    public String getArrivalTime() {
        return this.arrivalTime;
    }

    // setter - methods
    public void setId(int id) {
        this.id = id;
    }

    public void setStellplatz_id(int stellplatz_id) {
        this.stellplatz_id = stellplatz_id;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
    
    // todo
    private boolean checkInput() {
        //code
        return true;
    }
    // todo
    private void getAllLocationIDs() {
        //code 
    }
    // todo
    private boolean checkIn() {
        //code
        return true;
    }
    // todo
    private boolean checkOut() {
        // code
        return true;
    }

    
     
    @Override
    public String[] getAttributes() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAttributes'");
    }

    @Override
    public void display() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'display'");
    }

    //Produktklasse
}
