package SmartSystem;

public class Lights extends SmartDevice {
    private String deviceType = "Lights";
    private int light_level = 0;
    private String lightColor = "#00000";

    public Lights(){}

    public Lights(String name, String description) {
        super(name ,description);
        this.light_level = 0;
        this.lightColor = "#00000";
    }
    public Lights(String name, String description, boolean online, String deviceId, int light_level, String lightColor) {
        super(name, description, online, deviceId);
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
    public String getDeviceType() {
        return deviceType;
    }
}
