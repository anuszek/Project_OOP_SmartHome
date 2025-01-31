package SmartSystem;

public class Rumba extends SmartDevice{
    private String deviceType = "Rumba";

    public Rumba(){}
    public Rumba(String name, String deviceId, String description,boolean online) {
        super(name, deviceId, description,online);
    }
    public void startCleaning() {
        System.out.println("Rumba is cleaning");
    }
    public void stopCleaning() {
        System.out.println("Rumba stopped cleaning");
    }

    @Override
    public String getDeviceType() {
        return deviceType;
    }
}
