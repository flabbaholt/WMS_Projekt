package com.wms.model;

import com.wms.model.interfaces.Displayable;
import com.wms.util.Displayer;

public class Shelf implements Displayable{

    private int id;
    private int room_id;
    private String shelfIdentification;
    private String shelfCatagory;
    private String shelfCapacity;
    private String usedCapacity;
    private String shelfType;
    private String shelfCondition;

    private Shelf objectInstance;

    //erste Buchstabe groß
    private String[] attributes = {"ShelfIdentification","ShelfCatagory","ShelfCapacity","UsedCapacity","ShelfType","ShelfCondition"};

    //Definition des Konstruktors mit den Werten
    public Shelf(String shIde, String shCat, String shCap, String ocCap, String shTyp, String shCon){
        System.out.println("--- Model ---");
        System.out.println(">> Konstruktor-Shelf");

        this.shelfIdentification = shIde;
        this.shelfCatagory = shCat;
        this.shelfCapacity = shCap;
        this.usedCapacity = ocCap;
        this.shelfType = shTyp;
        this.shelfCondition = shCon;

        createInternObject(shelfIdentification, shelfCatagory, shelfCapacity, usedCapacity, shelfType, shelfCondition);
    }

    //Konstruktor mit String-Array
    public Shelf(String[] data){

        // erstmal Data checken
        System.out.println("--- Model ---");
        System.out.println(">> Konstruktor-Shelf String-Array");

        this.shelfIdentification = data[0];
        this.shelfCatagory = data[1];
        this.shelfCapacity = data[2];
        this.usedCapacity = data[3];
        this.shelfType = data[4];
        this.shelfCondition = data[5];

        createInternObject(shelfIdentification, shelfCatagory, shelfCapacity, usedCapacity, shelfType, shelfCondition);

    }

    public Shelf(String shIde, String shCat, String shCap, String ocCap, String shTyp, String shCon, boolean duplicated){
        
        System.out.println(">> Konstruktor-Shelf");

        this.shelfIdentification = shIde;
        this.shelfCatagory = shCat;
        this.shelfCapacity = shCap;
        this.usedCapacity = ocCap;
        this.shelfType = shTyp;
        this.shelfCondition = shCon;
    }

    //Set-Methoden
    public void setShelfIdentification(String shelfIdentification) {
        this.shelfIdentification = shelfIdentification;
    }
    public void setShelfCatagory(String shelfCatagory) {
        this.shelfCatagory = shelfCatagory;
    }
    public void sethelfCapacity(String shelfCapacity) {
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
    public String getShelfIdentification() {
        return shelfIdentification;
    }
    public String getShelfCatagory() {
        return shelfCatagory;
    }
    public String getShelfCapacity() {
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

        Shelf tempObject = new Shelf(shIde,shCat,shCap,ocCap,shTyp,shCon,true);
        objectInstance = tempObject;
        display();
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
        String shCapToInt = shelfCapacity;
        int capacityInt = Integer.parseInt(shCapToInt);
        System.out.println("Die Kapazität beträgt:"+capacityInt);

        String ocCapToInt = usedCapacity;
        int occpupiedCapacityInt = Integer.parseInt(ocCapToInt);
        System.out.println("Die belegte Kapazität beträgt:" +occpupiedCapacityInt);

        if (occpupiedCapacityInt < capacityInt){
            System.out.println("Es ist noch Platz frei.");

        } else{
            System.out.println("In dem Regal ist kein Platz mehr.");
        }
    }
}
