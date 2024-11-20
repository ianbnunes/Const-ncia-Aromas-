package com.cesarschool.constancia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/constancia";
    private static final String USER = "root";
    private static final String PASSWORD = "admin";

    @Bean
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
