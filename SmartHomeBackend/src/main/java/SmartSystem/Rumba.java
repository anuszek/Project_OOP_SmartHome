package SmartSystem;

public class Rumba extends SmartDevice{
    private String deviceType = "Rumba";
    private boolean cleaning;

    public Rumba(){}
    public Rumba(String name, String deviceId, String description,boolean online) {
        super(name, deviceId, description,online);
    }
    public void startCleaning() {
        cleaning = true;
        System.out.println("Rumba is cleaning");
    }
    public void stopCleaning() {
        cleaning = false;
        System.out.println("Rumba stopped cleaning");
    }

    @Override
    public String getDeviceType() {
        return deviceType;
    }
}
