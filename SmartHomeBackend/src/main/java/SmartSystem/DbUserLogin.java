package SmartSystem;

import java.sql.*;
import java.util.ArrayList;

public class DbUserLogin {
    private static final String DB_URL = "jdbc:sqlite:SmartHomeBackend/src/main/resources/DB/Users";

    public ArrayList<Boolean> checkUser(String username, String password) {
        String sql = "SELECT * FROM Users WHERE username = ? AND password = ?";
        ArrayList<Boolean> result = new ArrayList<>();
        boolean isValid;
        boolean isAdmin;
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                isValid = true;
                isAdmin = rs.getBoolean("isAdmin");
                result.add(isValid);
                result.add(isAdmin);
                return result;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            result.add(false);
        }
        return result;
    }

    public String returnUserData(String username) {
        String sql = "SELECT * FROM Users WHERE username = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("username");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public String register(String username, String password) {
        String sql = "INSERT INTO Users(username, password,isAdmin) VALUES(?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setBoolean(3, false);
            pstmt.executeUpdate();
            return "User registered successfully";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Error registering user";
    }
}