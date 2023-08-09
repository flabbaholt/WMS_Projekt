package com.wms.controller;

import com.wms.model.Product;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class DetailedViewController {

    @FXML
    private TextField productIDField;

    @FXML
    private TextField productNumberField;

    @FXML
    private TextField stellplatzIDField;

    @FXML
    private TextField productNameField;

    @FXML
    private TextField manufacturerField;

    @FXML
    private TextField arrivalTimeField;

    public void setProductDetails(Product product) {
        productIDField.setText(String.valueOf(product.getProductID()));
        productNumberField.setText(product.getProductNumber());
        stellplatzIDField.setText(product.getStellplatzID());
        productNameField.setText(product.getProductName());
        manufacturerField.setText(product.getManufacturer());
    }
}
