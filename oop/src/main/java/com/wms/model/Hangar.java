package com.wms.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.wms.util.SQL;
import com.wms.model.interfaces.Displayable;
import com.wms.util.Displayer;

public class Hangar implements Displayable {
    
    //private int id;
    private int lagerstandort_id; //noch nicht final
    private String hangar_identification;
    private float hangar_size;
    //wichtig: ersten Buchstaben immer groß schreiben
    private String[] attributes = {"Lagerstandort_id","Hangar_identification","Hangar_size","Hangar_secured"};

    //Konstruktor es fehlt noch containing Rooms die lagerstandort_id muss dann auch entfernt werden
    public Hangar(String haId, float haSi){
        System.out.println(">> Konstruktor-Hangar");
        this.hangar_identification = haId;
        this.hangar_size = haSi;
    }

    public Hangar(){}

    public Hangar (String haID){
        this.hangar_identification = haID;
    }
    //Set-Methods
    public void setHangar_identification(String hangar_identification) {
        this.hangar_identification = hangar_identification;
    }
    public void setHangar_size(float hangar_size) {
        this.hangar_size = hangar_size;
    }
    public void setLagerstandort_id(int lagerstandort_id) {
        this.lagerstandort_id = lagerstandort_id;
    }

    //Get-Methods
    public String getHangar_identification() {
        return hangar_identification;
    }
    public float getHangar_size() {
        return hangar_size;
    }
    public int getLagerstandort_id() {
        return lagerstandort_id;
    }
    public String[] getAttributes() {
        return attributes;
    }

    public void display(){
        System.out.println(">> Display-Hangar");

        Hangar hangar = new Hangar(hangar_identification,hangar_size);

        Displayer d = new Displayer();
        d.displayObject(hangar, getAttributes());
        
    }

    //Check-Input
    public boolean checkHangarInitialization(Hangar h){

        //

        return true;

    }

    //add to DB
    public void addToDB(){

        String value1 = getHangar_identification();
        float value2 = getHangar_size();

        String sql = "INSERT INTO Hangar (Hangar_Identification, Hangar_Size) VALUES (?, ?)";

        try (Connection conn = SQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, value1);
            pstmt.setFloat(2, value2);
            pstmt.executeUpdate();

            SQL.disconnect(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 
    public int getIDfromDB(){

        int result = -1;
        String sql = "SELECT * FROM Hangar WHERE Hangar_Identification = ?";

        try (Connection conn = SQL.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, this.hangar_identification);
            ResultSet resultSet = pstmt.executeQuery();
            
            if (resultSet.next()) {
                result = resultSet.getInt("ID");
                System.out.println(result);
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
    
    //Gibt alle Hangar_Identificationen zurueck
    public List<String> getHangarNamesFromDB() {
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
    
    public String getHangerIdentificationFromDB(int Shelf_ID){
        String result = "";

        String sql = "SELECT * FROM Hangar WHERE ID=?";
        System.out.println();

        try (Connection conn = SQL.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, Shelf_ID);
            ResultSet resultSet = pstmt.executeQuery();
            
            if (resultSet.next()) {
                result = resultSet.getString("Hangar_Identification");
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
}
