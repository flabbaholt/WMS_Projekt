package com.wms.model;

public class WarehouseLocation {
    private int id;
    private String companyName;
    private String country;
    private String city;
    private String postcode;
    private String street;
    private String houseNumber;

    // Konstrukter
    public WarehouseLocation(int id, String companyName, String country, String city, String postcode, String street, String houseNumber) {
        this.id = id;
        this.companyName = companyName;
        this.country = country;
        this.city = city;
        this.postcode = postcode;
        this.street = street;
        this.houseNumber = houseNumber;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostcode() {
        return this.postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getStreet() {
        return this.street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return this.houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    // todo
    public boolean checkInput() {
        // code
        return true;
    }

    //add to DB
 
    //update in DB

    //delete in DB
}
