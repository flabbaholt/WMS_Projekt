package com.wms.model;

import com.wms.model.interfaces.Displayable;

public class Product implements Displayable {
    
    Integer productID;
    String productNumber, stellplatzID, productName, manufacturer, arrivalTime;


    public Product(Integer productID, String productNumber, String stellplatzID, String productName, String manufacturer, String arrivalTime) {
        this.productID = productID;
        this.productNumber = productNumber;
        this.stellplatzID = stellplatzID;
        this.productName = productName;
        this.manufacturer = manufacturer;
        this.arrivalTime = arrivalTime;
    }


    public Integer getProductID() {
        return this.productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public String getProductNumber() {
        return this.productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public String getStellplatzID() {
        return this.stellplatzID;
    }

    public void setStellplatzID(String stellplatzID) {
        this.stellplatzID = stellplatzID;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getManufacturer() {
        return this.manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getArrivalTime() {
        return this.arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    // todo
    private boolean checkInput() {
        if(this.id >= 0 || this.stellplatz_id >= 0) {
            return false;
        } else if(this.productName ==null) {
            return false;
        } else if(this.manufacturer == null) {
            return false;
        } else if(arrivalTime == null) {
            return false;
        } else {
            return true;
        }
        
    }
    // todo
    private void getAllLocationIDs() {
        //code 
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
