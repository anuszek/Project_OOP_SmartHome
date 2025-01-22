package SmartSystem;

public class Oven extends SmartDevice {
    private String deviceType;
    private int temperature;

    public Oven(){}
    public Oven(String name, String deviceId, String description, int temperature) {
        super(name, deviceId, description);
        this.temperature = temperature;
    }
    public int getTemperature() {
        return temperature;
    }
    public void preheat(int temperature) {
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
