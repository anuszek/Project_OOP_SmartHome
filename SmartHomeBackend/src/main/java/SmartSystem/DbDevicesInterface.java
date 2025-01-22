package SmartSystem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbDevicesInterface {
    public static ArrayList<SmartDevice> getAllDevicesList() {
        final String dbUrl = "jdbc:sqlite:SmartHomeBackend/src/main/resources/DB/Devices";
        ArrayList<SmartDevice> devicesList = new ArrayList<>();
        final ArrayList<String> devicesTypesList = new ArrayList<>(List.of("blinds", "fridge", "light", "oven", "heating_system", "locks", "rumba", "sound_system"));
        try (Connection conn = DriverManager.getConnection(dbUrl);
             Statement stmt = conn.createStatement()) {
            for (String device : devicesTypesList) {
                try (ResultSet rsMin = stmt.executeQuery("SELECT MIN(id) AS min_id FROM " + device);
                     ResultSet rsMax = stmt.executeQuery("SELECT MAX(id) AS max_id FROM " + device)) {
                    if (rsMin.next() && rsMax.next()) {
                        int minId = rsMin.getInt("min_id");
                        int maxId = rsMax.getInt("max_id");
                        for (int i = minId; i <= maxId; i++) {
                            try (ResultSet rs = stmt.executeQuery("SELECT * FROM " + device + " WHERE id = " + i)) {
                                if (rs.next()) {
                                    switch (device) {
                                        case "blinds":
                                            devicesList.add(new Blinds(rs.getString("name"), rs.getString("deviceId"), rs.getString("deviceState"), rs.getInt("deviceLocation")));
                                            break;
                                        case "fridge":
                                            List<String> inventory = new ArrayList<>();
                                            try (ResultSet rsInventory = stmt.executeQuery("SELECT * FROM fridge_inventory WHERE fridgeId = " + i)) {
                                                while (rsInventory.next()) {
                                                    inventory.add(rsInventory.getString("item"));
                                                }
                                            }
                                            devicesList.add(new Fridge(rs.getString("name"), rs.getString("deviceId"), rs.getString("description"), rs.getInt("fridgeTemp"), rs.getInt("freezerTemp"), inventory, rs.getBoolean("Skyrim")));
                                            break;
                                        case "light":
                                            devicesList.add(new Lights(rs.getString("name"), rs.getString("deviceId"), rs.getString("description"), rs.getInt("brightness"), rs.getString("color")));
                                            break;
                                        case "oven":
                                            devicesList.add(new Oven(rs.getString("name"), rs.getString("deviceId"), rs.getString("description"), rs.getInt("temperature")));
                                            break;
                                        case "heating_system":
                                            devicesList.add(new HeatingSystem(rs.getString("name"), rs.getString("deviceId"), rs.getString("description"), rs.getInt("temperature")));
                                            break;
                                        case "locks":
                                            devicesList.add(new Locks(rs.getString("name"), rs.getString("deviceId"), rs.getString("description"), rs.getBoolean("isLocked")));
                                            break;
                                        case "rumba":
                                            devicesList.add(new Rumba(rs.getString("name"), rs.getString("deviceId"), rs.getString("description")));
                                            break;
                                        case "sound_system":
                                            devicesList.add(new SoundSystem(rs.getString("name"), rs.getString("deviceId"), rs.getString("description"), rs.getInt("volume")));
                                            break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return devicesList;
    }
}