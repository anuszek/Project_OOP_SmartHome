package SmartSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseUtil {
    private static final String DB_URL = "jdbc:sqlite:SmartHomeBackend/src/main/resources/DB/Devices";

    public static void storeDevice(String json) throws Exception {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Devices (deviceData) VALUES (?)")) {
            pstmt.setString(1, json);
            pstmt.executeUpdate();
        }
    }

    public static List<String> retrieveDevices() throws Exception {
        List<String> devices = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT deviceData FROM Devices")) {
            while (rs.next()) {
                devices.add(rs.getString("deviceData"));
            }
        }
        return devices;
    }
}