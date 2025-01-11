package SmartDevice;

public class Rumba extends SmartDevice{
    public Rumba(String name, String deviceId, String description) {
        super(name, deviceId, description);
    }
    public void startCleaning() {
        System.out.println("Rumba is cleaning");
    }
    public void stopCleaning() {
        System.out.println("Rumba stopped cleaning");
    }
}
