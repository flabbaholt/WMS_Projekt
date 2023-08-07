package com.wms.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

     //Konstruktor mit String-Array
     public ShelfSpace(String[] data){

        // erstmal Data checken
        System.out.println("--- Model ---");
        System.out.println(">> Konstruktor-ShelfSpace String-Array");

        this.spaceIdentification = data[0];
        this.spaceOccupied = data[1] != null;
        

       // createInternObject(spaceIdentification, spaceOccupied, sizeLimit, weightLimit);
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
    
}
