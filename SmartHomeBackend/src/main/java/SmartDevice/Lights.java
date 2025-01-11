package SmartDevice;

public class Lights extends SmartDevice {
    private int light_level;
    private String lightColor;
    public Lights(String name, String deviceId, String description){
    super(name, deviceId,description);
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
}
