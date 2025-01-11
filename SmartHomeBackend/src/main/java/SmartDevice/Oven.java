package SmartDevice;

public class Oven extends SmartDevice {
    private int temperature;
    public Oven(String name, String deviceId, String description) {
        super(name, deviceId, description);
    }
    public int getTemperature() {
        return temperature;
    }
    public void preheat(int temperature) {
        this.temperature = temperature;
    }

}
