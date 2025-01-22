package SmartSystem;

public class Locks extends SmartDevice {
    private String deviceType;
    private boolean locked;

    public Locks(){}

    public Locks(String name, String deviceId, String description, boolean locked) {
        super(name, deviceId, description);
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
    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }
    @Override
    public String getDeviceType() {
        return deviceType;
    }
}
