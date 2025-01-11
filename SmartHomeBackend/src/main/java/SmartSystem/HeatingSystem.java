package SmartSystem;

public class HeatingSystem extends SmartDevice {
    private int temperature;
    public HeatingSystem(String name, String deviceId, String description) {
        super(name, deviceId, description);
    }
    public int getTemperature() {
        return temperature;
    }
    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }
}
