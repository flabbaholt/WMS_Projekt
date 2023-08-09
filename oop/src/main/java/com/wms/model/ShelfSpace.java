package com.wms.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wms.SQL;
import com.wms.model.interfaces.Displayable;
import com.wms.util.Displayer;

public class ShelfSpace implements Displayable{
    
    private int id;
    private int shelf_id;
    private String spaceIdentification;
    private boolean spaceOccupied;
    

    private ShelfSpace objectInstance;

    //erste Buchstabe groß
    private String[] attributes = {"SpaceIdentification","SpaceOccupied","SizeLimit","WeightLimit"};

    //Definition des Konstruktors mit den Werten
    public ShelfSpace(int shelf_id, String spId){

        this.shelf_id = shelf_id;
        this.spaceIdentification = spId;
        this.spaceOccupied = false;
        
    }

    public ShelfSpace(){}
     //Konstruktor mit String-Array
     public ShelfSpace(String[] data){

        // erstmal Data checken
        System.out.println("--- Model ---");
        System.out.println(">> Konstruktor-ShelfSpace String-Array");

        this.spaceIdentification = data[0];
        this.spaceOccupied = data[1] != null;
        

       // createInternObject(spaceIdentification, spaceOccupied, sizeLimit, weightLimit);
    }

    public ShelfSpace(String spIde){
        this.spaceIdentification = spIde;
    }
    //Set-Methoden
    public void setShelfId(int id){
        this.shelf_id = id;
    }

    public void setSpaceIdentifiaction(String spaceIdentification) {
        this.spaceIdentification = spaceIdentification;
    }
    public void setSpaceOccupied(boolean spaceOccupied) {
        this.spaceOccupied = spaceOccupied;
    }
   
    //Get-Methoden
    public int getShelfID(){
        return shelf_id;
    }
    public String getSpaceIdentification() {
        return spaceIdentification;
    }
    public boolean getSpaceOccupied() {
        return spaceOccupied;
    }

    @Override
    public String[] getAttributes() {
        return attributes;
    }

    @Override
    public void display(){
        System.out.println("--- Model ---");
        System.out.println(">> Display-ShelfSpace");

       
        Displayer d = new Displayer();
        d.displayObject(this.objectInstance, getAttributes());
        
    }

    public void isObjectNull(){
        System.out.println(objectInstance==null);
    }

    public void addToDB(){

        int value1 = getShelfID();
        String value2 = getSpaceIdentification();
        boolean value3 = getSpaceOccupied();
        String sql = "INSERT INTO ShelfSpace (ID_SHELF, Space_Identification, Space_Occupied) VALUES (?, ?, ?)";

        try (Connection conn = SQL.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, value1);
            pstmt.setString(2, value2);
            pstmt.setBoolean(3, value3);
            pstmt.executeUpdate();

            SQL.disconnect(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<String> getShelfSapceNames(int shelfID) {
        List<String> shelfSpaceNames = new ArrayList<>();

        try (Connection connection = SQL.getConnection()){

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT Space_Identification FROM ShelfSpace WHERE ID_SHELF=? AND Space_Occupied=?");
            preparedStatement.setInt(1, shelfID);
            preparedStatement.setInt(2, 0);


            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                shelfSpaceNames.add(resultSet.getString("Space_Identification"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return shelfSpaceNames;
    }

    public int getIDfromDB(){

        int result = -1;

        String sql = "SELECT * FROM ShelfSpace WHERE Space_Identification=?";

        try (Connection conn = SQL.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, this.spaceIdentification);
            ResultSet resultSet = pstmt.executeQuery();
            
            if (resultSet.next()) {
                result = resultSet.getInt("ID");
            }else{ 
                System.out.println("No Data found");
            }

            resultSet.close();
            pstmt.close();
            SQL.disconnect(conn);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public void changeOccupation(int shelfSpace_ID){
        String sql = "SELECT * FROM ShelfSpace WHERE ID=?";

        boolean result = true;
        try (Connection conn = SQL.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, shelfSpace_ID);
            ResultSet resultSet = pstmt.executeQuery();
            
            if (resultSet.next()) {
                result = resultSet.getBoolean("Space_Occupied");
                System.out.println(result);
                if (result==true){
                    updateColumnValue(conn, shelfSpace_ID, false );
                }else{
                    updateColumnValue(conn, shelfSpace_ID, true );

                }
            }else{ 
                System.out.println("No Data found");
            }

            resultSet.close();
            pstmt.close();
            SQL.disconnect(conn);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateColumnValue(Connection conn, int shelfSpace_ID, boolean newValue) throws SQLException{
        String updateQuery = "UPDATE ShelfSpace SET Space_Occupied = ? WHERE id = ?";
        System.out.println(newValue);
        PreparedStatement updateStatement = conn.prepareStatement(updateQuery);
        updateStatement.setBoolean(1, newValue);
        updateStatement.setInt(2, shelfSpace_ID);
        updateStatement.executeUpdate();
    }
}
