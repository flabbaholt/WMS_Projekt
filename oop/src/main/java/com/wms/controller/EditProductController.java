package com.wms.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.wms.model.Product;
import com.wms.model.ShelfSpace;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.fxml.Initializable;


public class EditProductController implements Initializable {

    @FXML
    private TextField productIDField;
    @FXML
    private TextField productNumberField;
    @FXML
    private TextField stellplatzIDField;
    @FXML
    private ComboBox <String> stellplatzIDSelection;
    @FXML
    private TextField amountField;
    @FXML
    private TextField productNameField;
    @FXML
    private TextField manufacturerField;
    @FXML
    private Button submitEdit;
    @FXML
    private Button cancelEdit;

    private Product product;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize the controller
        submitEdit.setOnAction(event -> saveProduct());
        cancelEdit.setOnAction(event -> closeWindow());
    }

    // Method to set the product data in the TextFields
    public void setProductData(Product product) {
        this.product = product;
        setNewStellplatzIDValues();

        productIDField.setText(String.valueOf(product.getProductID()));
        //Das Feld ProductID darf nicht bearbeitet werden
        productIDField.setDisable(true);

        productNumberField.setText(product.getProductNumber());
        stellplatzIDField.setText(product.getStellplatzID());
        amountField.setText(String.valueOf(product.getAmount()));
        productNameField.setText(product.getProductName());
        manufacturerField.setText(product.getManufacturer());
    }
    // Method to save the edited product data
    @FXML
    private void saveProduct() {
        if (product != null) {
            //Neues Objekt, das verwendet wird, um die jeweilige Occupation des Stellplatzes zu ändern
            ShelfSpace shelfSpace = new ShelfSpace();
            int oldStellPlatzID = shelfSpace.getShelfSpaceIDfromDB(product.getStellplatzID());
            shelfSpace.changeOccupation(oldStellPlatzID);

            // Update the product with edited data
            product.setProductID(Integer.parseInt(productIDField.getText()));
            product.setProductNumber(productNumberField.getText());
            product.setStellplatzID(stellplatzIDSelection.getValue());
            product.setAmount(Integer.parseInt(amountField.getText()));
            product.setProductName(productNameField.getText());
            product.setManufacturer(manufacturerField.getText());


            //Ändert den boolean wert des neuen Wertes im Shelfspace auf belegt
            int newStellPlatzID = shelfSpace.getShelfSpaceIDfromDB(stellplatzIDSelection.getValue());
            shelfSpace.changeOccupation(newStellPlatzID);

            // Save the updated product to the database
            product.update(); //Implement into product class

            // Close the window after saving
            submitEdit.getScene().getWindow().hide();
        }
    }

    private void closeWindow() {
        // Close the window (Stage) when the cancel button is clicked
        submitEdit.getScene().getWindow().hide();
    }
    
    private void setNewStellplatzIDValues(){
        //Get Shelf-ID
        ShelfSpace shelfSpace = new ShelfSpace();
        System.out.println("Stellplatz IDentif: "+product.getStellplatzID());
        int shelfID = shelfSpace.getShelfIDfromDB(product.getStellplatzID());

        List<String> freeShelfSpaces = shelfSpace.getShelfSapceNames(shelfID);
        stellplatzIDSelection.getItems().addAll(freeShelfSpaces);
    }

}
