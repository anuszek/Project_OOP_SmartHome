package SmartDevice;

public class SoundSystem extends SmartDevice{
    private int volume;
    private String connectionType; // Bluetooth, Wi-Fi or smth idk
    public SoundSystem(String deviceName, String deviceId, String description) {
        super(deviceName, deviceId, description);
    }
    public int getVolume() {
        return volume;
    }
    public void setVolume(int volume) {
        this.volume = volume;
    }
    public String getConnectionType() {
        return connectionType;
    }
    public void setConnectionType(String connectionType) {
        this.connectionType = connectionType;
    }
}
