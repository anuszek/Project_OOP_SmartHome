package SmartSystem;

public class Rumba extends SmartDevice{
    private String deviceType = "Rumba";
    private boolean cleaning = false;

    public Rumba(){}
    public Rumba(String name, String description) {
        super(name, description);
    }

    public Rumba(String name, String description, boolean online, String deviceId, boolean cleaning) {
        super(name, description, online, deviceId);
        this.cleaning = cleaning;
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
