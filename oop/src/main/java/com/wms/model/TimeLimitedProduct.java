package com.wms.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.wms.SQL;

public class TimeLimitedProduct extends Product {
    
    // attributes
    private String expiryDate;

    // constructer
    public TimeLimitedProduct(Integer amount, String productNumber, int shelfSpace_ID, String productName, String manufacturer, String expiryDate) {
        super(amount, productNumber, shelfSpace_ID, productName, manufacturer);
        this.expiryDate = expiryDate;
    }

    // getter - methods
    public String getExpiryDate() {
        return this.expiryDate;
    }

    
    // setter - methods
    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void addToDB(){
        int value1 = this.shelfSpace_ID;
        String value2 = this.productNumber;
        int value3 = this.amount;
        String value4 = this.productName;
        String value5 = this.manufacturer;
        String value6 = expiryDate;

        String sql = "INSERT INTO Product (ID_SHELFSPACE, ProductNumber, Amount, ProductName, Manufacturer, ExpiryDate ) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = SQL.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, value1);
            pstmt.setString(2, value2);
            pstmt.setInt(3, value3);
            pstmt.setString(4, value4);
            pstmt.setString(5, value5);
            pstmt.setString(6, value6);
            
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
