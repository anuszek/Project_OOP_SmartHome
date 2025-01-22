package SmartSystem;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Blinds extends SmartDevice {
    @JsonProperty
    private String deviceType;
    @JsonProperty
    private int position;

    public Blinds(){}

    public Blinds(String name, String deviceId, String description, int position) {
        super(name,deviceId,description);
        this.position = position;
    }
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }
    @Override
    public String getDeviceType() {
        return deviceType;
    }
}
