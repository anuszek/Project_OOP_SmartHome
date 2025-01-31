package SmartSystem;

public class Oven extends SmartDevice {
    private String deviceType = "Oven";
    private int temperature;

    public Oven(){}
    public Oven(String name, String deviceId, String description,boolean online, int temperature) {
        super(name, deviceId, description,online);
        this.temperature = temperature;
    }
    public int getTemperature() {
        return temperature;
    }
    public void preheat(int temperature) {
        this.temperature = temperature;
    }

    @Override
    public String getDeviceType() {
        return deviceType;
    }

}
