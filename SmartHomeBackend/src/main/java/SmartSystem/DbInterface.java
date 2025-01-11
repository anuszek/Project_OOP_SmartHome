package SmartSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbInterface {
    private final String dbUrl = "jdbc:sqlite:src/main/resources/DB/Devices.db";

    public static String getDeviceName(String deviceName) {
        final String dbUrl = "jdbc:sqlite:src/main/resources/DB/Devices.db";
        try (Connection conn = DriverManager.getConnection(dbUrl);
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM Rumba where deviceName = '" + deviceName + "'");
            if (rs.next()) {
                return rs.getString("deviceName");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
