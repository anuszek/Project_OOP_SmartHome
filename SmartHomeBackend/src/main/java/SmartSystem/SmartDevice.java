package SmartSystem;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class SmartDevice {
    @JsonProperty
    private String name;
    @JsonProperty
    private final String deviceId;
    private boolean online;
    @JsonProperty
    private String description;

    public SmartDevice(){
        this.deviceId = "";
    }

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
    public abstract void setDeviceType(String deviceType);
    public abstract String getDeviceType();
}
