package SmartSystem;

import java.sql.*;

public class DbUserLogin {
    public boolean checkUser(String username, String password) {
        final String dbUrl= "jdbc:mysql:/src/main/resources/DB/Users";
        try {
            Connection conn = DriverManager.getConnection(dbUrl);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Users WHERE username = '" + username + "' AND password = '" + password + "'");
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return false;
    }
}
