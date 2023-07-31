package com.wms.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Callback;

public class DashboardController {
 
    public void initialize() {
        // Erstelle die Zelle, um Buttons im TableView anzuzeigen
        buttonColumn.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
            @Override
            public TableCell<String, String> call(TableColumn<String, String> param) {
                return new TableCell<String, String>() {
                    final Button button = new Button();

                    {
                        button.setOnAction(event -> {
                            String rowData = getTableView().getItems().get(getIndex());
                            System.out.println("Button clicked for row: " + rowData);
                        });
                    }

                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            button.setText(item);
                            setGraphic(button);
                        }
                    }
                };
            }
        });

        // Fülle den TableView mit den Button-Daten
        ObservableList<String> data = FXCollections.observableArrayList();
        for (int i = 0; i < 10; i++) {
            data.add("Button " + (i + 1));
        }
        tableView.setItems(data);
    }
}
