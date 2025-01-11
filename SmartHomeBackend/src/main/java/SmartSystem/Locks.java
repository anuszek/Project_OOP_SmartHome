package SmartSystem;

public class Locks extends SmartDevice {
    private boolean locked;
    public Locks(String name, String deviceId, String description) {
        super(name, deviceId, description);
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
}
