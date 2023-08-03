package com.wms;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    
    public Connection databaseLink;

    public Connection getDBConnection(){

        final String databaseName = "WMS";
        final String databaseUser = "WMS_access";
        final String databasePassword = "SuperSicheresPasswort";
        final String host = "45.85.217.198";
        final String port = "3306";
        final String url = "jdbc:mysql://" + host + ":" + port + "/" + databaseName;

        try{
            System.out.println("Connecting to MySQL");
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Attempting to establish database connection...");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
            System.out.println("Database connection established successfully!");
            
        }
        catch(Exception e){
            System.out.println("Could not connect to MySQL");
            e.printStackTrace();
            e.getCause();
        }

        return databaseLink;
    }
}
