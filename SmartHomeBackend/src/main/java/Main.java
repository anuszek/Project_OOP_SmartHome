import SmartSystem.DbDevicesInterface;

public class Main {
    public static void main(String[] args) {
        String device1 = "{\"name\":\"Living Room Light\",\"deviceId\":\"light1\",\"description\":\"Main light in the living room\",\"position\":0,\"deviceType\":\"Lights\"}";
        String device2 = "{\"name\":\"Kitchen Fridge\",\"deviceId\":\"fridge1\",\"description\":\"Fridge in the kitchen\",\"position\":0,\"deviceType\":\"Fridge\"}";
        String device3 = "{\"name\":\"Bedroom Heater\",\"deviceId\":\"heater1\",\"description\":\"Heater in the bedroom\",\"position\":0,\"deviceType\":\"HeatingSystem\"}";

        DbDevicesInterface.insertDevice("Lights",device1);
        DbDevicesInterface.insertDevice("Fridge",device2);
        DbDevicesInterface.insertDevice("HeatingSystem",device3);

        System.out.println("Test devices inserted into the database.");
    }
}