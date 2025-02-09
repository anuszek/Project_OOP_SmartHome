package SmartSystem;

public class Locks extends SmartDevice {
    private String deviceType = "Locks";
    private boolean locked = false;

    public Locks(){}

    public Locks(String name, String description) {
        super(name, description);
        this.locked = false;
    }
    public Locks(String name, String description, String online, String deviceId, boolean locked) {
        super(name, description, online, deviceId);
        this.locked = locked;
    }
    public boolean isLocked() {
        return locked;
    }
    public void lock() {
        locked = true;
    }
    public void unlock() {
        locked = false;
    }

    @Override
    public String getDeviceType() {
        return deviceType;
    }
}
