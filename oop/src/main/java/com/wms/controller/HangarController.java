package com.wms.controller;


import java.net.URL;
import java.util.ResourceBundle;

import com.wms.model.Hangar;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class HangarController implements Initializable {

    @FXML
    protected TextField hangerIdentification;
    @FXML
    protected TextField hangerSize;

    @FXML
    private Button submitAdd;
    @FXML
    private Button cancelAdd;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize the controller
        submitAdd.setOnAction(event -> saveHangar());
        cancelAdd.setOnAction(event -> closeWindow());
    }

    private void saveHangar(){
        String haId = hangerIdentification.getText();
        float haSi = Float.parseFloat(hangerSize.getText());
        
        // Objekt wird erzeugt
        Hangar h = new Hangar(haId, haSi);
        
        h.addToDB();
        closeWindow();
    }
    
    private void closeWindow() {
        // Close the window (Stage) when the cancel button is clicked
        submitAdd.getScene().getWindow().hide();
    }
}