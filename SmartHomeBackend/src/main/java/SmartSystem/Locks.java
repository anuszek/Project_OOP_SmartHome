package SmartSystem;

public class Locks extends SmartDevice {
    private String deviceType = "Locks";
    private boolean locked;

    public Locks(){}

    public Locks(String name, String deviceId, String description,boolean online, boolean locked) {
        super(name, deviceId, description,online);
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
