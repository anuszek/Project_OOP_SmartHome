package SmartSystem;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Blinds extends SmartDevice {
    @JsonProperty
    private String deviceType = "Blinds";
    @JsonProperty
    private int position = 50;

    public Blinds(){}

    public Blinds(String name, String description) {
        super(name,description);
        this.position = 50;
    }
    public Blinds(String name, String description, boolean online, String deviceId, int position) {
        super(name, description, online, deviceId);
        this.position = position;
    }
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String getDeviceType() {
        return deviceType;
    }
}
