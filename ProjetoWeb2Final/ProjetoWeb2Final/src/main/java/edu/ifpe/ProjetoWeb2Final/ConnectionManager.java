package edu.ifpe.ProjetoWeb2Final;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private static final String URL = "jdbc:mysql://localhost:3306/fabrica"; 
    private static final String USER = "root"; 
    private static final String PASSWORD = "root"; 

    public static Connection getCurrentConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

