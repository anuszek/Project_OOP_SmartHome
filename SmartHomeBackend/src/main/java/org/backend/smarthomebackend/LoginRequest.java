package org.backend.smarthomebackend;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginRequest {
    private String username;
    private String password;
    private final String dbUrl = "jdbc:sqlite:src/main/resources/DB/Users.db";

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


}
