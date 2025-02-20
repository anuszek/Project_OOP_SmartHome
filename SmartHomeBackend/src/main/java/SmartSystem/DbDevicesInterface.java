package SmartSystem;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Logger;

public class DbDevicesInterface {
    private static final Logger logger = Logger.getLogger(DbDevicesInterface.class.getName());
    private static final String DB_URL = "jdbc:sqlite:SmartHomeBackend/src/main/resources/DB/Devices";

    public static void addDevice(String deviceType, String deviceData) {
        String sql = "INSERT INTO Devices(deviceType, deviceData) VALUES(?,?)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, deviceType);
            pstmt.setString(2, deviceData);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            logger.severe("Error inserting device: " + ex.getMessage());
        }
    }
    public static ArrayList<String> getAllDevicesList() {
        ArrayList<String> devicesJsonList = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Devices")) {

            while (rs.next()) {
                String deviceData = rs.getString("deviceData");
                devicesJsonList.add(deviceData);
            }
        } catch (SQLException ex) {
            logger.severe("Error retrieving devices: " + ex.getMessage());
        }

        return devicesJsonList;
    }
    public static void deleteDevice(String deviceID) {
        String sql = "DELETE FROM Devices WHERE deviceType= ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, deviceID);
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            logger.severe("Error deleting device: " + ex.getMessage());
        }
    }
    public static void updateDevice(String deviceId, String newDeviceData) {
        String sql = "UPDATE Devices SET deviceData = ? WHERE deviceType = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newDeviceData);
            pstmt.setString(2, deviceId);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            logger.severe("Error updating device: " + ex.getMessage());
        }
    }
    public static String getDevice(String deviceId) {

        String sql = "SELECT * FROM Devices WHERE deviceType = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, deviceId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return (rs.getString("deviceData"));
            }
        } catch (SQLException ex) {
            logger.severe("Error extracting device: " + ex.getMessage());
        }
        return null;
    }

    public static int countDevices() {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM Devices";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            count = rs.getInt(1);
        } catch (SQLException ex) {
            logger.severe("Error counting devices: " + ex.getMessage());
        }

        return count;
    }
    }