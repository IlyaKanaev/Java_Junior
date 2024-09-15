package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Db {
    private static final String URL = "jdbc:mysql://localhost:3306";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    // создаем объект класса Connection
    // обращаемся к DriverManager
    // вызываем метод getConnection с параметрами в виде наших констант

    public static void con() {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)){

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
