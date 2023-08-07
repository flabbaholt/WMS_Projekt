package com.wms.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.wms.model.Hangar;
import com.wms.model.Shelf;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class ShelfController implements Initializable {

    @FXML
    protected TextField regalbezeichnung;
    @FXML
    protected TextField anzahlPlaetze;

    @FXML
    private ComboBox<String> selectHangar;
    @FXML
    private ComboBox<String> regaltyp;
    @FXML
    private ComboBox<String> regalkategorie;

    @FXML
    private Button submitAdd;
    @FXML
    private Button cancelAdd;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Shelf shelf = new Shelf();
        List<String> hangarNames = shelf.getHangarNames();
        selectHangar.getItems().addAll(hangarNames);

        //setzt die Dropdown-Werte für Regal-Kategorien
        ObservableList<String> categories = FXCollections.observableArrayList();
        categories.addAll("gekuehlt","beheizt","raumtemperatur");
        regalkategorie.setItems(categories);

        //setzt die Dropdown-Werte für Regal-Typen
        ObservableList<String> types = FXCollections.observableArrayList();
        types.addAll("Flüssig","Fest");
        regaltyp.setItems(types);
        

        // Initialize the controller
        submitAdd.setOnAction(event -> saveShelf());
        cancelAdd.setOnAction(event -> closeWindow());
    }

    private void saveShelf(){

        String hangar_Identification = selectHangar.getValue();
        Hangar h = new Hangar(hangar_Identification);
        int iDHan = h.getIDfromDB();

        System.out.println("Auswahl: " + iDHan);

        String shIde = regalbezeichnung.getText();
        int maCap = Integer.parseInt(anzahlPlaetze.getText());
        String shCat = regalkategorie.getValue();
        String shTyp = regaltyp.getValue();

        // Objekt wird erzeugt
        Shelf shelf = new Shelf(iDHan,shIde,maCap, shTyp, shCat);
        
        
        shelf.addToDB();
        closeWindow();
    }
    
    private void closeWindow() {
        // Close the window (Stage) when the cancel button is clicked
        submitAdd.getScene().getWindow().hide();
    } 
    
}
