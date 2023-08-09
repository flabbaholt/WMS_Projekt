package com.wms.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.wms.SQL;

public class TemperatureRelatedProduct extends Product {
    // attributes 
    private String climateCategory; //entweder beheizt, gekühlt oder raumtemperatur
    private double temperature;

    // constructer 

    public TemperatureRelatedProduct(Integer amount, String productNumber, int shelfSpace_ID, String productName, String manufacturer, String climateCategory, double temperature) {
        super(amount, productNumber, shelfSpace_ID, productName, manufacturer);
        this.climateCategory = climateCategory;
        this.temperature = temperature;
    }

    // getter -  methods
    public String getClimateCategory() {
        return this.climateCategory;
    }

    public double getTemperature() {
        return this.temperature;
    }

    // setter - methods                                                                                                                                                                                                                                             
    public void setClimateCategory(String climateCategory) {
        this.climateCategory = climateCategory;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void addToDB(){
        int value1 = this.shelfSpace_ID;
        String value2 = this.productNumber;
        int value3 = this.amount;
        String value4 = this.productName;
        String value5 = this.manufacturer;

        String value6 = climateCategory;
        double value7 = temperature;

        String sql = "INSERT INTO Product (ID_SHELFSPACE, ProductNumber, Amount, ProductName, Manufacturer, ClimateCategory, Temperature) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = SQL.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, value1);
            pstmt.setString(2, value2);
            pstmt.setInt(3, value3);
            pstmt.setString(4, value4);
            pstmt.setString(5, value5);
            pstmt.setString(6, value6);
            pstmt.setDouble(7, value7);
            pstmt.executeUpdate();

            SQL.disconnect(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ShelfSpace s = new ShelfSpace();
        s.changeOccupation(shelfSpace_ID);
        setShelfSpace_Identification(shelfSpace_ID);
    }
}
