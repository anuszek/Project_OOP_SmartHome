package SmartSystem;

public class SmartDevice {
    private String name;
    private final String deviceId;
    private boolean online;
    private String description;
    public SmartDevice(String name, String deviceId, String description) {
        this.name = name;
        this.deviceId = deviceId;
        this.description = description;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDeviceId() {
        return deviceId;
    }
    public void turnOn() {
        online = true;
    }
    public void turnOff() {
        online = false;
    }
    public boolean isOnline() {
        return online;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }
}
