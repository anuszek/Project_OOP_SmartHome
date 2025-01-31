import SmartSystem.*;
import SmartSystem.JsonUtil;
import SmartSystem.DatabaseUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> inv = Arrays.asList("potato", "tomato", "onion", "carrot", "cabbage");

        // Create test devices
        ArrayList<SmartDevice> devices = new ArrayList<>();
        ArrayList<String> test = new ArrayList<>();
        SoundSystem livingRoomSpeaker = new SoundSystem("Living Room Speaker", "SS001", "Main speaker in the living room", true, 50);
        Rumba rumba = new Rumba("Rumba", "R001", "Cleaning robot", false);
        Blinds blinds = new Blinds("Blinds", "B001", "Blinds in the living room", true, 50);
        Fridge fridge = new Fridge("Fridge", "F001", "Fridge in the kitchen", false, 7,-2,inv, false );
        Oven oven = new Oven("Oven", "O001", "Oven in the kitchen", false, 0);
        Locks frontDoor = new Locks("Front Door", "L001", "Front door lock", true, true);
        Lights livingRoomLights = new Lights("Living Room Lights", "L001", "Lights in the living room", true, 50, "white");
        HeatingSystem heatingSystem = new HeatingSystem("Heating System", "H001", "Heating system in the house", true, 20);

        devices.add(livingRoomSpeaker);
        devices.add(rumba);
        devices.add(blinds);
        devices.add(fridge);
        devices.add(oven);
        devices.add(frontDoor);
        devices.add(livingRoomLights);
        devices.add(heatingSystem);

        for (SmartDevice device : devices) {
            try {
               String te = JsonUtil.serialize(device);
                DatabaseUtil.storeDevice(te);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }
}