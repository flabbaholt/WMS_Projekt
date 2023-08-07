package com.wms.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.wms.SQL;
import com.wms.model.interfaces.Displayable;
import com.wms.util.Displayer;

public class Shelf implements Displayable{

    private int id_hangar;
    private String shelfIdentification;
    private String shelfCatagory;
    private int shelfCapacity;
    private String usedCapacity;
    private String shelfType;
    private String shelfCondition;

    private Shelf objectInstance;

    //erste Buchstabe groß
    private String[] attributes = {"ShelfIdentification","ShelfCatagory","ShelfCapacity","UsedCapacity","ShelfType","ShelfCondition"};


    //Konstruktor mit String-Array
    public Shelf(String[] data){

        // erstmal Data checken
        System.out.println("--- Model ---");
        System.out.println(">> Konstruktor-Shelf String-Array");

        this.shelfIdentification = data[0];
        this.shelfCatagory = data[1];
        //this.shelfCapacity = data[2];
        this.usedCapacity = data[3];
        this.shelfType = data[4];
        this.shelfCondition = data[5];

       // createInternObject(shelfIdentification, shelfCatagory, shelfCapacity, usedCapacity, shelfType, shelfCondition);

    }

    public Shelf(int iDHan, String shIde, int maCap, String shTyp, String shCat ){
        this.id_hangar = iDHan;
        this.shelfIdentification  = shIde;
        this.shelfCapacity = maCap;
        this.shelfType = shTyp;
        this.setShelfCatagory(shCat);
    }

    public Shelf(){}
    
    //Set-Methoden
    public void setIdHangar(int id){
        this.id_hangar = id;
    }
    public void setShelfIdentification(String shelfIdentification) {
        this.shelfIdentification = shelfIdentification;
    }
    public void setShelfCatagory(String shelfCatagory) {
        this.shelfCatagory = shelfCatagory;
    }
    public void sethelfCapacity(int shelfCapacity) {
        this.shelfCapacity = shelfCapacity;
    }
    public void setUsedCapacity(String occupiedCapacity) {
        this.usedCapacity = occupiedCapacity;
    }
    public void setShelfType(String shelfType) {
        this.shelfType = shelfType;
    }
    public void setShelfCondition(String shelfCondition) {
        this.shelfCondition = shelfCondition;
    }

    //Get-Methoden
    public int getIdHangar(){
        return id_hangar;
    }
    public String getShelfIdentification() {
        return shelfIdentification;
    }
    public String getShelfCatagory() {
        return shelfCatagory;
    }
    public int getShelfCapacity() {
        return shelfCapacity;
    }
    public String getUsedCapacity() {
        return usedCapacity;
    }
    public String getShelfType() {
        return shelfType;
    }
    public String getShelfCondition() {
        return shelfCondition;
    }

    //Overloaded
    public void createInternObject(String shIde, String shCat, String shCap, String ocCap, String shTyp, String shCon){
        System.out.println("--- Model ---");
        System.out.println(">> Objekt-Values werden gesetted");

       // Shelf tempObject = new Shelf(shIde,shCat,shCap,ocCap,shTyp,shCon,true);
       // objectInstance = tempObject;
       // display();
    }
     //Overloaded

     @Override
     public String[] getAttributes() {
         return attributes;
     }

    @Override
    public void display(){
        System.out.println("--- Model ---");
        System.out.println(">> Display-Shelf");

        Displayer d = new Displayer();
        d.displayObject(this.objectInstance, getAttributes());
        isFull();        
    }

    public void isObjectNull(){
        System.out.println(objectInstance==null);
    }

    //Methode um zu gucken, ob Regal noch Platz hat
    public void isFull(){
        //String shCapToInt = shelfCapacity;
       // int capacityInt = Integer.parseInt(shCapToInt);
        //System.out.println("Die Kapazität beträgt:"+capacityInt);

        String ocCapToInt = usedCapacity;
        int occpupiedCapacityInt = Integer.parseInt(ocCapToInt);
        System.out.println("Die belegte Kapazität beträgt:" +occpupiedCapacityInt);

        //if (occpupiedCapacityInt < capacityInt){
            System.out.println("Es ist noch Platz frei.");

        //} else{
            System.out.println("In dem Regal ist kein Platz mehr.");
        
    }


    public void addToDB(){
        int value1 = getIdHangar();
        String value2 = getShelfIdentification();
        int value3 = getShelfCapacity();
        String value4 = getShelfType();
        String value5 = getShelfCatagory();


        String sql = "INSERT INTO Shelf (ID_HANGAR, Shelf_Identification, Max_Capacity, Type, Category) VALUES (?, ?, ?, ?, ?)";

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
    }

    public List<String> getHangarNames() {
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
}
