package com.wms.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.wms.model.Hangar;
import com.wms.model.Product;
import com.wms.model.Shelf;
import com.wms.model.ShelfSpace;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class DetailedViewController implements Initializable{

    @FXML
    private TextField productIDField;
    @FXML
    private TextField productNumberField;
    @FXML
    private TextField stellplatzIDField;
    @FXML
    private TextField amountField;
    @FXML
    private TextField productNameField;
    @FXML
    private TextField manufacturerField;
    @FXML 
    private TextField regal;
    @FXML 
    private TextField lager;

    @FXML
    private Button cancelButton;


    public void setProductDetails(Product product) {
        productIDField.setText(String.valueOf(product.getProductID()));
        productNumberField.setText(product.getProductNumber());
        stellplatzIDField.setText(product.getStellplatzID());
        amountField.setText(String.valueOf(product.getAmount()));
        productNameField.setText(product.getProductName());
        manufacturerField.setText(product.getManufacturer());
        regal.setText(getShelfIdentification(product.getStellplatzID()));
        lager.setText(getHangarIdentification(getShelfIdentification(product.getStellplatzID())));

    }

    //liefert die RegalID des zugehoerigen Stellplatzes
    public int getShelfID(String shellSpace_Identification){
        ShelfSpace s = new ShelfSpace();
        return s.getShelfIDfromDB(shellSpace_Identification);
    }

    public String getShelfIdentification(String shellSpace_Identification){
        Shelf shelf = new Shelf();
        return shelf.getShelfIdentification(getShelfID(shellSpace_Identification));
    }

    public String getHangarIdentification(String shelfIdentification){

        Shelf s = new Shelf();
        int hangar_ID = s.getHangarID(shelfIdentification);

        Hangar h = new Hangar();
        return h.getHangerIdentificationFromDB(hangar_ID);
    }

    private void closeWindow() {
        // Close the window (Stage) when the cancel button is clicked
        cancelButton.getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cancelButton.setOnAction(event -> closeWindow()); 
    }
}
