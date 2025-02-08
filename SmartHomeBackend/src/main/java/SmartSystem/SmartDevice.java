package SmartSystem;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class SmartDevice {
    @JsonProperty
    private String name;
    private String description;
    @JsonProperty
    private boolean online;
    private String deviceId;
    @JsonProperty

    private static int deviceCount = DbDevicesInterface.countDevices();

    {
        deviceCount++;
        deviceId = "Device" + deviceCount;
    }

    public SmartDevice(){
        this.deviceId = "";
    }

    public SmartDevice(String name, String description) {
        this.name = name;
        this.deviceId = deviceId;
        this.description = description;
        this.online = false;
    }

    public SmartDevice(String name, String description, boolean online, String deviceId) {
        this.name = name;
        this.deviceId = deviceId;
        this.description = description;
        this.online = online;
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
    public abstract String getDeviceType();
}
