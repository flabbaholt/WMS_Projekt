package com.wms.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ProductController implements Initializable {

    @FXML
    private TextField amountField;
    @FXML
    private TextField stellplatzIDField;
    @FXML
    private TextField productNumberField;
    @FXML
    private TextField productNameField;
    @FXML
    private TextField manufacturerField;
    @FXML
    private Button submitAdd;
    @FXML
    private Button cancelAdd;

    //Es fehlt noch die Unterscheidung zu !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    
    @FXML 
    public void handleSubmitButton(MouseEvent event){
        

        

    }

    private void closeWindow() {
        // Close the window (Stage) when the cancel button is clicked
        cancelAdd.getScene().getWindow().hide();
    }

    private void saveProduct(){

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize the controller
        submitAdd.setOnAction(event -> saveProduct());
        cancelAdd.setOnAction(event -> closeWindow());        
    }
}
