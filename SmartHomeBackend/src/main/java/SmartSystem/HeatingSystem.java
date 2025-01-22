package SmartSystem;

public class HeatingSystem extends SmartDevice {
    private int temperature;
    private String deviceType;

    public HeatingSystem(){}

    public HeatingSystem(String name, String deviceId, String description, int temperature) {
        super(name, deviceId, description);
        this.temperature = temperature;
    }
    public int getTemperature() {
        return temperature;
    }
    public void setTemperature(int temperature) {
        this.temperature = temperature;
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
