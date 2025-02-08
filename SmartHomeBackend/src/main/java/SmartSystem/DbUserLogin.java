package SmartSystem;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

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
        if (checkIfUserExists(username)) {
            return "User already exists";
        }

        String sql = "INSERT INTO Users (username, password,isAdmin) VALUES (?, ?, false)";
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            conn.setAutoCommit(false); // Disable auto-commit

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, username);
                pstmt.setString(2, password);
                pstmt.executeUpdate();
                conn.commit();
                return "User registered successfully";
            } catch (SQLException e) {

                e.printStackTrace();
                return "Error occurred during registration";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error occurred during registration";
        }
    }

    public boolean checkIfUserExists(String username) {
        String query = "SELECT COUNT(*) FROM Users WHERE username = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static HashMap<String, Boolean> getAllUsersList() {
        HashMap<String, Boolean> usersList = new HashMap<>();
        String sql = "SELECT username, isAdmin FROM Users";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String username = rs.getString("username");
                boolean isAdmin = rs.getBoolean("isAdmin");
                usersList.put(username, isAdmin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersList;
    }
    public static Integer deleteUser(String username) {
        String sql = "DELETE FROM Users WHERE username = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                return 1; // User deleted successfully
            } else {
                return 0; // User not found
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1; // Error occurred
        }
    }
    public static String grantAdmin(String username) {
        String sql = "UPDATE Users SET isAdmin = ? WHERE username = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setBoolean(1, true);
            pstmt.setString(2, username);
            pstmt.executeUpdate();
            return "User successfully elevated";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error elevating user";
        }
    }
}