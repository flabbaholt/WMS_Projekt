package com.wms.controller;

import java.io.IOException;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.fxml.Initializable;
import javafx.util.Callback;

//This class is the controller for the dashboard.fxml file - it handles all the events that occur in the dashboard view
public class DashboardController implements Initializable {

    @FXML
    private TableView<Product> productTableView;
    @FXML
    private TableColumn<Product, Integer> productIDColumn;
    @FXML
    private TableColumn<Product, String> productNumberColumn;
    @FXML
    private TableColumn<Product, String> stellplatzIDColumn;
    @FXML
    private TableColumn<Product, String> productNameColumn;
    @FXML
    private TableColumn<Product, String> manufacturerColumn;
    @FXML
    private TableColumn<Product, String> arrivalTimeColumn;
    @FXML
    private TextField searchTextField;

    //ObservableList - This is a list that enables listeners to track changes when they occur
    ObservableList<Product> productSearchModelObservableList = FXCollections.observableArrayList();

    //Initialize the controller class - This method is automatically called after the fxml file has been loaded
    @Override
    public void initialize(URL url, ResourceBundle resource){
        
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connection = connectNow.getDBConnection();

        //SQL Query - Executed in the database
        String productViewQuery = "SELECT ProductID, ProductNumber, StellplatzID, ProductName, Manufacturer, arrivalTime FROM Product";

        //Try to execute the query - if it fails, print the stack trace
        try{
            Statement statement = connection.createStatement();
            ResultSet queryOutput = statement.executeQuery(productViewQuery);

            //Iterate through the query output and create Product objects
            while(queryOutput.next()){

                Integer queryProductID = queryOutput.getInt("ProductID");
                String queryProductNumber = queryOutput.getString("ProductNumber");
                String queryStellplatzID = queryOutput.getString("StellplatzID");
                String queryProductName = queryOutput.getString("ProductName");
                String queryManufacturer = queryOutput.getString("Manufacturer");
                String queryArrivalTime = queryOutput.getString("arrivalTime");

                //Populate the ObservableList
                productSearchModelObservableList.add(new Product(queryProductID, queryProductNumber, queryStellplatzID, queryProductName, queryManufacturer, queryArrivalTime));
            }

            //PropertValueFactory - Set the column in the tableview to the correct value
            //The table column name must be the same as the variable name in the model class
            productIDColumn.setCellValueFactory(new PropertyValueFactory<>("productID"));
            productNumberColumn.setCellValueFactory(new PropertyValueFactory<>("productNumber"));
            stellplatzIDColumn.setCellValueFactory(new PropertyValueFactory<>("stellplatzID"));
            productNameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
            manufacturerColumn.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
            arrivalTimeColumn.setCellValueFactory(new PropertyValueFactory<>("arrivalTime"));

            //Set the cell factory to display the data in the correct format
            productTableView.setItems(productSearchModelObservableList);

            //Wrap the ObservableList in a FilteredList (initially display all data)
            FilteredList<Product> filteredData = new FilteredList<>(productSearchModelObservableList, b -> true);
            //Compare product number, stellplatz ID, product name, manufacturer and arrival time of every product with the search value
            searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(productSearchModel -> {
   
                    //If no search value is entered, display all data
                    if(newValue == null || newValue.isEmpty() || newValue.isBlank()){
                        return true;
                    }
                    
                    String searchValue = newValue.toLowerCase();

                    //Compare product number, stellplatz ID, product name, manufacturer and arrival time of every product with the search value + use toLowerCaseorNA method to handle null values and avoid NullPointerException
                    if (StringUtils.toLowerCaseOrNA(productSearchModel.getProductNumber()).contains(searchValue.toLowerCase())) {
                        return true; // Filter matches product number
                    } else if (StringUtils.toLowerCaseOrNA(productSearchModel.getStellplatzID()).contains(searchValue.toLowerCase())) {
                        return true; // Filter matches stellplatz ID
                    } else if (StringUtils.toLowerCaseOrNA(productSearchModel.getProductName()).contains(searchValue.toLowerCase())) {
                        return true; // Filter matches product name
                    } else if (StringUtils.toLowerCaseOrNA(productSearchModel.getManufacturer()).contains(searchValue.toLowerCase())) {
                        return true; // Filter matches manufacturer
                    } else if (StringUtils.toLowerCaseOrNA(productSearchModel.getArrivalTime()).contains(searchValue.toLowerCase())) {
                        return true; // Filter matches arrival time
                    } else {
                        return false; // Does not match
                    }

                });
            });

            SortedList<Product> sortedData = new SortedList<>(filteredData);

            //Bind the SortedList comparator to the TableView comparator
            sortedData.comparatorProperty().bind(productTableView.comparatorProperty());

            //Add sorted (and filtered) data to the table
            productTableView.setItems(sortedData);
        }catch(SQLException e){
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
    }


    //EditProduct Code
    private Stage editProductStage;

    @FXML
    private void handleEditProductButtonClicked(MouseEvent event) {
    Product selectedProduct = productTableView.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
        openEditProductWindow(selectedProduct);
        updateTableData();
        }
    }

    private void openEditProductWindow(Product product) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/wms/views/EditProduct.fxml"));
            
            Parent root = loader.load();
    
            // Get the controller instance from the loader
            EditProductController editProductController = loader.getController();
    
            // Set the product data in the EditProductController
            editProductController.setProductData(product);
    
            // Create a new stage for the Edit_Product window
            editProductStage = new Stage();
            editProductStage.setTitle("Produkt bearbeiten");
            editProductStage.setScene(new Scene(root));
            editProductStage.initModality(Modality.APPLICATION_MODAL); // Block input events to other windows
            editProductStage.showAndWait(); // Show the window and wait for it to be closed
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    //Update Table
    @FXML
    private void handleRefreshDataButtonClicked(ActionEvent event) {
        // Call the method to update the table data
        updateTableData();
    }

    // Method to update the table data
    private void updateTableData() {
        // Clear the current data in the ObservableList
        productSearchModelObservableList.clear();

        // Perform the database query and populate the ObservableList with the updated data
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connection = connectNow.getDBConnection();

        // SQL Query - Executed in the database
        String productViewQuery = "SELECT ProductID, ProductNumber, StellplatzID, ProductName, Manufacturer, arrivalTime FROM Product";

        // Try to execute the query - if it fails, print the stack trace
        try {
            Statement statement = connection.createStatement();
            ResultSet queryOutput = statement.executeQuery(productViewQuery);

            // Iterate through the query output and create Product objects
            while (queryOutput.next()) {
                Integer queryProductID = queryOutput.getInt("ProductID");
                String queryProductNumber = queryOutput.getString("ProductNumber");
                String queryStellplatzID = queryOutput.getString("StellplatzID");
                String queryProductName = queryOutput.getString("ProductName");
                String queryManufacturer = queryOutput.getString("Manufacturer");
                String queryArrivalTime = queryOutput.getString("arrivalTime");

                // Populate the ObservableList
                productSearchModelObservableList.add(new Product(queryProductID, queryProductNumber, queryStellplatzID, queryProductName, queryManufacturer, queryArrivalTime));
            }

            // The rest of the code to set the cell factory and update the table remains the same

        } catch (SQLException e) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
    }
    

    //Method to return to the main menu

    //Method to update the product

    //Method to delete the product

    //FXMl-Methode, die den add_hangar Button handled. Es soll der view: Add_Hangar aufgerufen werden

    //FXMl-Methode, die den add_shelf Button handled. Es soll der view: Add_Shelf aufgerufen werden

    //FXMl-Methode, die den add_shelfspace Button handled. Es soll der view: Add_ShelfSpace aufgerufen werden
    
    //FXMl-Methode, die den add_product Button handled. Es soll der view: Add_Product aufgerufen werden

}
