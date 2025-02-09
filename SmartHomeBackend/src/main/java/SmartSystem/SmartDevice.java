package SmartSystem;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class SmartDevice {
    @JsonProperty
    private String name;
    private String description;
    @JsonProperty
    private String online;
    private String deviceId;
    private static String deviceID2;
    @JsonProperty

    private static int deviceCount = DbDevicesInterface.countDevices();

    {
        deviceCount++;
        deviceID2 = "Device" + deviceCount;
    }

    public SmartDevice(){
        this.deviceId = deviceID2;
    }

    public SmartDevice(String name, String description) {
        this.name = name;
        this.deviceId = deviceID2;
        this.description = description;
        this.online = "false";
    }

    public SmartDevice(String name, String description, String online, String deviceId) {
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

    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }
    public abstract String getDeviceType();
}
