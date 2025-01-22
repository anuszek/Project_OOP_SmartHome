package SmartSystem;

public class Lights extends SmartDevice {
    private String deviceType;
    private int light_level;
    private String lightColor;

    public Lights(){}

    public Lights(String name, String deviceId, String description, int light_level, String lightColor) {
        super(name, deviceId,description);
        this.light_level = light_level;
        this.lightColor = lightColor;
    }
    public int getLightLevel() {
        return light_level;
    }
    public void setLightLevel(int light_level) {
        this.light_level = light_level;
    }
    public String getLightColor() {
        return lightColor;
    }
    public void setLightColor(String lightColor) {
        this.lightColor = lightColor;
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
