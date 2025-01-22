//package SmartSystem;
//
//import SmartSystem.*;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//
//public class DbInterface {
//    private final String devicesDbUrl = "jdbc:sqlite:src/main/resources/DB/Devices";
//    private final String usersDbUrl = "jdbc:sqlite:src/main/resources/DB/Users";
//    private final String TimeTableDbUrl = "jdbc:sqlite:src/main/resources/DB/Time_Tables";
//
//    public ArrayList<SmartDevice> getDevicesArray(){
//
//        ArrayList<SmartDevice> devices = new ArrayList<>();
//        try (Connection conn = DriverManager.getConnection(devicesDbUrl);
//             Statement stmt = conn.createStatement()) {
//            ResultSet rs = stmt.executeQuery("SELECT * FROM Devices");
//            while (rs.next()) {
//                String name = rs.getString("name");
//                String deviceId = rs.getString("device_id");
//                String description = rs.getString("description");
//                devices.add(new SmartDevice(name, deviceId, description));
//            }
//        } catch (SQLException ex) {
//            throw new RuntimeException(ex);
//        }
//        return devices;
//    }
//}
