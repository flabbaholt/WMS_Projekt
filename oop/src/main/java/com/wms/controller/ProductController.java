package com.wms.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.wms.model.Hangar;
import com.wms.model.Product;
import com.wms.model.Shelf;
import com.wms.model.ShelfSpace;
import com.wms.model.TemperatureRelatedProduct;
import com.wms.model.TimeLimitedProduct;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class ProductController implements Initializable {

    @FXML
    private TextField amountField;
    @FXML
    private TextField productNameField;
    @FXML
    private TextField productNumberField;
    @FXML
    private TextField manufacturerField;

    @FXML
    private ComboBox<String> productTypeDropDown;
    @FXML
    private ComboBox<String> climateCategoryDropDown;
    @FXML 
    private ComboBox<String> hangarDropDown;
    @FXML 
    private ComboBox<String> shelfDropDown;
    @FXML 
    private ComboBox<String> shelfSpaceDropDown;

    @FXML
    private TextField expiryDateField;
    @FXML
    private TextField temperatureField;

    @FXML
    private Text expiryDateText;
    @FXML
    private Text climateCategoryText;
    @FXML
    private Text temperatureText;
    
    @FXML
    private Button submitAdd;
    @FXML
    private Button cancelAdd;

    private void closeWindow() {
        // Close the window (Stage) when the cancel button is clicked
        cancelAdd.getScene().getWindow().hide();
    }

    private void saveProduct(){

        //Eingaben extrahieren
        String productNumber =  productNumberField.getText();
        String productName = productNameField.getText();
        String manufacturer = manufacturerField.getText();
        int amount = Integer.parseInt(amountField.getText());
        String productType = productTypeDropDown.getValue();

        String shelfSpace_identification = shelfSpaceDropDown.getValue();

        ShelfSpace s = new ShelfSpace(shelfSpace_identification);
        int shelfSpace_ID = s.getIDfromDB();


        if (productType == "Haltbarkeitslimitiert"){
            System.out.println("halt");
            String expDate = expiryDateField.getText();

            TimeLimitedProduct prod = new TimeLimitedProduct(amount, productNumber,  shelfSpace_ID, productName,  manufacturer, expDate);
            prod.addToDB();

        }else if(productType == "Temperaturbedingt"){

            double temperature = Double.parseDouble(temperatureField.getText());
            System.out.println(temperature);
            String climateCategory = climateCategoryDropDown.getValue();

            System.out.println(climateCategoryDropDown.getValue()+"\t"+temperature);

            TemperatureRelatedProduct prod = new TemperatureRelatedProduct(amount, productNumber,  shelfSpace_ID, productName,  manufacturer, climateCategory, temperature);
            prod.addToDB();

        }else if(productType == "Beides"){ 

        }else{

            Product prod = new Product(amount, productNumber, shelfSpace_ID, productName, manufacturer);
            prod.addToDB();

        }
        closeWindow();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize the controller
        hideAllExtraItems();
        submitAdd.setOnAction(event -> saveProduct());
        cancelAdd.setOnAction(event -> closeWindow());       
        
        //setzt die Dropdown-Werte für Regal-Kategorien
        ObservableList<String> categories = FXCollections.observableArrayList();
        categories.addAll("Normal","Haltbarkeitslimitiert","Temperaturbedingt");
        productTypeDropDown.setItems(categories);

        //setzt die Dropdown-Werte für Regal-Typen
        ObservableList<String> types = FXCollections.observableArrayList();
        types.addAll("gekühlt","beheizt");
        climateCategoryDropDown.setItems(types);

        //setzt die Dropdown-Werte für HangarDropDown
        Hangar h = new Hangar();
        List<String> hangarNames = h.getHangarNamesFromDB();
        hangarDropDown.getItems().addAll(hangarNames);
    }

    @FXML
    private void handleProductTypeButton(ActionEvent event) {
        String selectedOption = productTypeDropDown.getValue();
        if (selectedOption == null) {
            // Der Benutzer hat noch keinen Wert ausgewählt.
            // Sie können hier entsprechende Aktionen vornehmen oder Fehlermeldungen anzeigen.
            System.out.println("null");
            climateCategoryDropDown.setVisible(false);
            expiryDateField.setVisible(false);
            temperatureField.setVisible(false);
            expiryDateText.setVisible(false);
            climateCategoryText.setVisible(false);
            temperatureText.setVisible(false);
        } else {
            // Der Benutzer hat eine Option ausgewählt, führen Sie entsprechende Aktionen aus.
            switch (selectedOption) {
                case "Haltbarkeitslimitiert":
                    climateCategoryDropDown.setVisible(false);
                    expiryDateField.setVisible(true);
                    temperatureField.setVisible(false);
                    expiryDateText.setVisible(true);
                    climateCategoryText.setVisible(false);
                    temperatureText.setVisible(false);
                    break;
                case "Temperaturbedingt":
                    climateCategoryDropDown.setVisible(true);
                    expiryDateField.setVisible(false);
                    temperatureField.setVisible(true);
                    expiryDateText.setVisible(false);
                    climateCategoryText.setVisible(true);
                    temperatureText.setVisible(true);
                    break;
                default:
                    climateCategoryDropDown.setVisible(false);
                    expiryDateField.setVisible(false);
                    temperatureField.setVisible(false);
                    expiryDateText.setVisible(false);
                    climateCategoryText.setVisible(false);
                    temperatureText.setVisible(false);
            }
        }
    }

    public void hideAllExtraItems(){
        climateCategoryDropDown.setVisible(false);
        expiryDateField.setVisible(false);
        temperatureField.setVisible(false);
        expiryDateText.setVisible(false);
        climateCategoryText.setVisible(false);
        temperatureText.setVisible(false);
    }

    @FXML
    public void hangarSelectionChanged(ActionEvent event){
        
        //löscht die Werte aus voriger Auswahl des Hangars
        shelfDropDown.getItems().clear();

        //ID des ausgewählten Hangars extrahieren
        String hangar_identification = hangarDropDown.getValue();
        Hangar temp_hangar = new Hangar(hangar_identification);
        //Zieht sich mithilfe der Hangeridentifikation die dazugehoerige Hangar ID
        int hangar_ID = temp_hangar.getIDfromDB();

        //setzt die Dropdown-Werte für Shelf-Drop Down
        Shelf shelf = new Shelf();
        List<String> shelfNames = shelf.getShelfNames(hangar_ID);
        shelfDropDown.getItems().addAll(shelfNames);
    }

    @FXML
    public void shelfSelectionChanged(ActionEvent event){
        
        //löscht die Werte aus voriger Auswahl des Hangars
        shelfSpaceDropDown.getItems().clear();

        //ID des ausgewählten Hangars extrahieren
        String shelf_identification = shelfDropDown.getValue();
        Shelf temp_shelf = new Shelf(shelf_identification);
        //Zieht sich mithilfe der Hangeridentifikation die dazugehoerige Hangar ID
        int shelf_ID = temp_shelf.getIDfromDB();

        //setzt die Dropdown-Werte für ShelfSpace-Drop Down
        ShelfSpace shelfSpace = new ShelfSpace();
        List<String> shelfSpaces = shelfSpace.getShelfSapceNames(shelf_ID);
        shelfSpaceDropDown.getItems().addAll(shelfSpaces);
    }


}
