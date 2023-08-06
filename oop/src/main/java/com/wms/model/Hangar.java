package com.wms.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.wms.SQL;
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
 
    //update in DB

    //delete in DB
}
