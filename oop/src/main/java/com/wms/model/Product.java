package com.wms.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.wms.DatabaseConnection;
import com.wms.SQL;
import com.wms.model.interfaces.Displayable;

public class Product implements Displayable {
    
    int productID, amount, shelfSpace_ID;
    String productNumber, productName, manufacturer ,shelfSpace_Identification,stellplatzID,arrivalTime;

    public Product(Integer productID, String productNumber, String stellplatzID, int amount, String productName, String manufacturer, String arrivalTime) {
        this.productID = productID;
        this.productNumber = productNumber;
        this.stellplatzID = stellplatzID;
        this.amount = amount;
        this.productName = productName;
        this.manufacturer = manufacturer;
        this.arrivalTime = arrivalTime;
    }

    public Product(int amount, String productNumber, int shelfSpace_ID, String productName, String manufacturer) {
        this.amount = amount;
        this.productNumber = productNumber;
        this.shelfSpace_ID = shelfSpace_ID;
        this.productName = productName;
        this.manufacturer = manufacturer;
    }

    public String getStellplatzID() {
        return this.stellplatzID;
    }

    public void setStellplatzID(String stellplatzID) {
        this.stellplatzID = stellplatzID;
    } 
    public String getArrivalTime() {
        return this.arrivalTime;
    }

    public Integer getAmount() {
        return this.amount;
    }

    public void setAmout(int amount) {
        this.amount = amount;
    }

    public String getProductNumber() {
        return this.productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public int getshelfSpace_ID() {
        return this.shelfSpace_ID;
    }

    public void setshelfSpace_ID(int shelfSpace_ID) {
        this.shelfSpace_ID = shelfSpace_ID;
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

    public int getProductID(){
        return this.productID;
    }
    
    public String getShelfSpace_Identification(){
        return this.shelfSpace_Identification;
    }

    public void setShelfSpace_Identification(String shelfSpace_Identification){
        this.shelfSpace_Identification = shelfSpace_Identification;
    }
    
    public void setProductID(int pID){
        this.productID = pID;
    }
    
    // Maybe add a column viewable or something like that to the database
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

    public void addToDB(){

        int value1 = this.shelfSpace_ID;
        String value2 = this.productNumber;
        int value3 = this.amount;
        String value4 = this.productName;
        String value5 = this.manufacturer;

        String sql = "INSERT INTO Product (ID_SHELFSPACE, ProductNumber, Amount, ProductName, Manufacturer) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = SQL.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, value1);
            pstmt.setString(2, value2);
            pstmt.setInt(3, value3);
            pstmt.setString(4, value4);
            pstmt.setString(5, value5);
            pstmt.executeUpdate();

            SQL.disconnect(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ShelfSpace s = new ShelfSpace();
        s.changeOccupation(shelfSpace_ID);
        setShelfSpace_Identification(shelfSpace_ID);
    }

    public void setShelfSpace_Identification(int shelfSpace_ID){

        String sql = "SELECT Space_Identification FROM ShelfSpace WHERE id = ?";

        try (Connection conn = SQL.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, shelfSpace_ID);

            ResultSet resultSet = pstmt.executeQuery();


            String valueToInsert = "";
            if (resultSet.next()) {
                valueToInsert = resultSet.getString("Space_identification");
            }

            String insertQuery = "UPDATE Product SET StellplatzID = ? WHERE ID_SHELFSPACE = ?";
            PreparedStatement insertStatement = conn.prepareStatement(insertQuery);
            insertStatement.setString(1, valueToInsert);
            insertStatement.setInt(2, shelfSpace_ID);
            insertStatement.executeUpdate();

            SQL.disconnect(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<String> getShelfSpaces(int regalID) {
        List<String> hangarNames = new ArrayList<>();

        try (Connection connection = SQL.getConnection()){
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT Hangar_Identification FROM Hangar");

            while (resultSet.next()) {
                hangarNames.add(resultSet.getString("Hangar_Identification"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hangarNames;
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

}
