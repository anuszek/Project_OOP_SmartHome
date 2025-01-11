package SmartSystem;

public class Blinds extends SmartDevice {
    private int position;
    public Blinds(String name, String deviceId, String description) {
        super(name,deviceId,description);
    }
    public int getPosition() {
        return position;
    }
    public void setPosition(int position) {
        this.position = position;
    }
}
