package com.wms.controller;


import com.wms.model.Hangar;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class HangarController {

    @FXML
    protected TextField hangerIdentification;
    @FXML
    protected TextField hangerSize;

    
    @FXML
    public void handleHinzufuegenButtonClicked(ActionEvent event){
        System.out.println(">> Add button was pressed");
        
        String haId = hangerIdentification.getText();
        String haSi = hangerSize.getText();
        
        // Objekt wird erzeugt
        Hangar h = new Hangar(haId, haSi);
        h.display();
        //spaeter h.save()
        
        try {
            // Laden der .fxml-Datei
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/oop/views/Dashboard_Scene.fxml"));
            Parent root = loader.load();

            // Erstellen einer neuen Scene
            Scene scene = new Scene(root);

            // Erstellen einer neuen Stage (Fenster)
            Stage stage = new Stage();
            stage.setTitle("Dashboard Lager");
            stage.setScene(scene);  

            //App.loadFXML("/com/oop/views/Dashboard_Scene");

            // Anzeigen der Stage
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        

    }
}