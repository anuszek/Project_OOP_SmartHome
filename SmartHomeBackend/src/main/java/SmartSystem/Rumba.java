package SmartSystem;

public class Rumba extends SmartDevice{
    private String deviceType;

    public Rumba(){}
    public Rumba(String name, String deviceId, String description) {
        super(name, deviceId, description);
    }
    public void startCleaning() {
        System.out.println("Rumba is cleaning");
    }
    public void stopCleaning() {
        System.out.println("Rumba stopped cleaning");
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
