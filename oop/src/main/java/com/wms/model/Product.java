package com.wms.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.wms.DatabaseConnection;
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
        if(this.productID >= 0 || this.stellplatzID == null) {
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

    // Method to delete a Product row from the database based on ProductID
    public void delete(int productID) {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection con = connectNow.getDBConnection();

        // Get the database connection
        try (Connection connection = con) {
            // Prepare the delete query
            String query = "DELETE FROM Product WHERE ProductID=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, productID);

            // Execute the delete query
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted == 1) {
                System.out.println("Product with ProductID " + productID + " deleted successfully!");
            } else {
                System.out.println("Failed to delete product with ProductID " + productID + ".");
            }
        } catch (SQLException e) {
            System.err.println("Error deleting product: " + e.getMessage());
        }
    }

    // Method to update the Product data in the database
    public void update() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection con = connectNow.getDBConnection();
        // Get the database connection
        try (Connection connection = con) {
            // Prepare the update query
            String query = "UPDATE Product SET ProductNumber=?, StellplatzID=?, ProductName=?, Manufacturer=? WHERE ProductID=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, this.productNumber);
            statement.setString(2, this.stellplatzID);
            statement.setString(3, this.productName);
            statement.setString(4, this.manufacturer);
            statement.setInt(5, this.productID);

            // Execute the update query
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 1) {
                System.out.println("Product updated successfully!");
            } else {
                System.out.println("Failed to update product.");
            }
        } catch (SQLException e) {
            System.err.println("Error updating product: " + e.getMessage());
        }
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
