package SmartSystem;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Blinds extends SmartDevice {
    @JsonProperty
    private String deviceType = "Blinds";
    @JsonProperty
    private int position;

    public Blinds(){}

    public Blinds(String name, String deviceId, String description,boolean online, int position) {
        super(name,deviceId,description,online);
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
