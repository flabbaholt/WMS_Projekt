package com.wms.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import java.util.logging.Level;

import com.wms.DatabaseConnection;
import com.wms.model.Product;
import com.wms.util.StringUtils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.fxml.Initializable;
import javafx.util.Callback;


public class EditProductController implements Initializable {

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
        productIDField.setText(String.valueOf(product.getProductID()));
        productNumberField.setText(product.getProductNumber());
        stellplatzIDField.setText(product.getStellplatzID());
        // amountField.setText(product.getAmount());
        productNameField.setText(product.getProductName());
        manufacturerField.setText(product.getManufacturer());
    }
    // Method to save the edited product data
    @FXML
    private void saveProduct() {
        if (product != null) {
            // Update the product with edited data
            product.setProductID(Integer.parseInt(productIDField.getText()));
            product.setProductNumber(productNumberField.getText());
            product.setStellplatzID(stellplatzIDField.getText());
            //product.setAmount(Integer.parseInt(amountField.getText()));
            product.setProductName(productNameField.getText());
            product.setManufacturer(manufacturerField.getText());

            // Save the updated product to the database
            product.update(); //Implement into product class

            // Close the window after saving
            submitEdit.getScene().getWindow().hide();
        } else {
            // Handle null product (optional, if needed)
        }
    }

    private void closeWindow() {
        // Close the window (Stage) when the cancel button is clicked
        submitEdit.getScene().getWindow().hide();
    }
    
}
