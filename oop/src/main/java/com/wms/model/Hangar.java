package com.wms.model;

import com.wms.model.interfaces.Displayable;
import com.wms.util.Displayer;

public class Hangar implements Displayable {
    
    //private int id;
    private int lagerstandort_id; //noch nicht final
    private String hangar_identification;
    private String hangar_size;
    private Shelf[] containing_Shelfs;
    //wichtig: ersten Buchstaben immer groß schreiben
    private String[] attributes = {"Lagerstandort_id","Hangar_identification","Hangar_size","Hangar_secured"};

    //Konstruktor es fehlt noch containing Rooms die lagerstandort_id muss dann auch entfernt werden
    public Hangar(String haId, String haSi){
        System.out.println(">> Konstruktor-Hangar");
        this.hangar_identification = haId;
        this.hangar_size = haSi;
    }

    //Set-Methods
    public void setContaining_Shelfs(Shelf[] containing_Shelfs) {
        this.containing_Shelfs = containing_Shelfs;
    }
    public void setHangar_identification(String hangar_identification) {
        this.hangar_identification = hangar_identification;
    }
    public void setHangar_size(String hangar_size) {
        this.hangar_size = hangar_size;
    }
    public void setLagerstandort_id(int lagerstandort_id) {
        this.lagerstandort_id = lagerstandort_id;
    }

    //Get-Methods
    public Shelf[] getContaining_Shelfs() {
        return containing_Shelfs;
    }
    public String getHangar_identification() {
        return hangar_identification;
    }
    public String getHangar_size() {
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
 
    //update in DB

    //delete in DB
}
