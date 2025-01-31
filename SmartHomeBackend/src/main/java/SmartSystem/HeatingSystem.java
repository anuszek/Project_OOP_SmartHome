package SmartSystem;

public class HeatingSystem extends SmartDevice {
    private int temperature;
    private String deviceType = "HeatingSystem";

    public HeatingSystem(){}

    public HeatingSystem(String name, String deviceId, String description,boolean online, int temperature) {
        super(name, deviceId, description,online);
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
