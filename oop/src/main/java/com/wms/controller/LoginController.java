package com.oop.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginController {
    
    public void start(){

    }
    @FXML
    public void handleLoginButton(ActionEvent event){
        System.out.println(">> Login button was pressed");

        try {
            // Laden der .fxml-Datei
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/oop/views/Add_Hangar.fxml"));
            
            Parent root = loader.load();

            // Erstellen einer neuen Scene
            Scene scene = new Scene(root);

            // Erstellen einer neuen Stage (Fenster)
            Stage stage = new Stage();
            stage.setTitle("Lager Overview");
            stage.setScene(scene);

            // Anzeigen der Stage
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

}
