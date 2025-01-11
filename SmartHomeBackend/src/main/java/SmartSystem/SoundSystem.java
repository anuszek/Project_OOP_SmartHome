package SmartSystem;

public class SoundSystem extends SmartDevice{
    private int volume;
    public SoundSystem(String deviceName, String deviceId, String description) {
        super(deviceName, deviceId, description);
    }
    public int getVolume() {
        return volume;
    }
    public void setVolume(int volume) {
        this.volume = volume;
    }
}
