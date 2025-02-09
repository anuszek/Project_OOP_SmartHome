package SmartSystem;

public class HeatingSystem extends SmartDevice {
    private int temperature;
    private String deviceType = "HeatingSystem";

    public HeatingSystem(){}

    public HeatingSystem(String name, String description) {
        super(name, description);
        this.temperature = 22;
    }
    public HeatingSystem(String name, String description, String online, String deviceId, int temperature) {
        super(name, description, online, deviceId);
        this.temperature = temperature;
    }
    public int getTemperature() {
        return temperature;
    }
    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }
    @Override
    public String getDeviceType() {
        return deviceType;
    }
}
