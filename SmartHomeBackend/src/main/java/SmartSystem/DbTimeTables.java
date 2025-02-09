package SmartSystem;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

public class DbTimeTables {
    private static final Logger logger = Logger.getLogger(DbDevicesInterface.class.getName());
    private static final String DB_URL = "jdbc:sqlite:SmartHomeBackend/src/main/resources/DB/Time_Tables";

    public static void addTimeTable(String time, String event, String deviceID) {
        String sql = "INSERT INTO TimeTable(time, event, device) VALUES(?,?,?)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(3, time);
            pstmt.setString(2, deviceID);
            pstmt.setString(1, time);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            logger.severe("Error inserting time table: " + ex.getMessage());
        }
    }

    public static void deleteTimeTable(String time, String event, String deviceID) {
        String sql = "DELETE FROM TimeTable WHERE (time, event, device) = (?,?,?)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, time);
            pstmt.setString(2, event);
            pstmt.setString(3, deviceID);
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            logger.severe("Error deleting time table: " + ex.getMessage());
        }
    }

    public static ArrayList<String> getActiveTimeTables() {
        ArrayList<String> timeTables = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM TimeTable")) {

            while (rs.next()) {
                String time = rs.getString("time");
                String event = rs.getString("event");
                String device = rs.getString("device");
                timeTables.add(time + "ඞ " + event + "ඞ " + device);
            }
        } catch (SQLException ex) {
            logger.severe("Error retrieving devices: " + ex.getMessage());
        }
        return timeTables;
    }
}
