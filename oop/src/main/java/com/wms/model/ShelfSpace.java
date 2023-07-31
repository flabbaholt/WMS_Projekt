package com.oop.model;

import com.oop.model.interfaces.Displayable;
import com.oop.util.Displayer;

public class ShelfSpace implements Displayable{
    
    private int id;
    private int shelf_id;
    private String spaceIdentification;
    private boolean spaceOccupied;
    private String sizeLimit;
    private String weightLimit;

    private ShelfSpace objectInstance;

    //erste Buchstabe groß
    private String[] attributes = {"SpaceIdentification","SpaceOccupied","SizeLimit","WeightLimit"};

     //Definition des Konstruktors mit den Werten
     public ShelfSpace(String spId, boolean spOc, String siLi, String weLi){
        System.out.println("--- Model ---");
        System.out.println(">> Konstruktor-ShelfSpace");

        this.spaceIdentification = spId;
        this.spaceOccupied = spOc;
        this.sizeLimit = siLi;
        this.weightLimit = weLi;

        createInternObject(spaceIdentification, spaceOccupied, sizeLimit, weightLimit);
    }

     //Konstruktor mit String-Array
     public ShelfSpace(String[] data){

        // erstmal Data checken
        System.out.println("--- Model ---");
        System.out.println(">> Konstruktor-ShelfSpace String-Array");

        this.spaceIdentification = data[0];
        this.spaceOccupied = data[1] != null;
        this.sizeLimit = data[2];
        this.weightLimit = data[3];

        createInternObject(spaceIdentification, spaceOccupied, sizeLimit, weightLimit);
    }

    public ShelfSpace(String spId, boolean spOc, String siLi, String weLi, boolean duplicated){

        System.out.println(">> Konstruktor-ShelfSpace");

        this.spaceIdentification = spId;
        this.spaceOccupied = spOc;
        this.sizeLimit = siLi;
        this.weightLimit = weLi;
    }

    //Set-Methoden
    public void setSpaceIdentifiaction(String spaceIdentification) {
        this.spaceIdentification = spaceIdentification;
    }
    public void setSpaceOccupied(boolean spaceOccupied) {
        this.spaceOccupied = spaceOccupied;
    }
    public void setSizeLimit(String sizeLimit) {
        this.sizeLimit = sizeLimit;
    }
    public void setWeightLimit(String weightLimit) {
        this.weightLimit = weightLimit;
    }
   
    //Get-Methoden
    public String getSpaceIdentification() {
        return spaceIdentification;
    }
    public boolean getSpaceOccupied() {
        return spaceOccupied;
    }
    public String getSizeLimit() {
        return sizeLimit;
    }
    public String getWeightLimit() {
        return weightLimit;
    }
    
    //Overloaded
    public void createInternObject(String spId, boolean spOc, String siLi, String weLi){
        System.out.println("--- Model ---");
        System.out.println(">> Objekt-Values werden gesetted");

        ShelfSpace tempObject = new ShelfSpace(spId, spOc, siLi, weLi, true);
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
        System.out.println(">> Display-ShelfSpace");

       
        Displayer d = new Displayer();
        d.displayObject(this.objectInstance, getAttributes());
        
    }

    public void isObjectNull(){
        System.out.println(objectInstance==null);
    }
    
}
