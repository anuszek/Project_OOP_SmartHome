package SmartSystem;

public class Oven extends SmartDevice {
    private String deviceType = "Oven";
    private int temperature = 22;

    public Oven(){}
    public Oven(String name, String description) {
        super(name, description);
        this.temperature = 22;
    }
    public Oven (String name, String description, String online, String deviceId,int temperature) {
        super(name, description, online, deviceId);
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
