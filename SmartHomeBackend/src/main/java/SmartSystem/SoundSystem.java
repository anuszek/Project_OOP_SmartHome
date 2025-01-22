package SmartSystem;

import java.util.HashMap;

public class SoundSystem extends SmartDevice{
    private HashMap<String,Integer> playlist = new HashMap<>();
    private String deviceType;
    private int volume;

    public SoundSystem(){}
    public SoundSystem(String deviceName, String deviceId, String description, int volume) {
        super(deviceName, deviceId, description);
        this.volume = volume;
    }
    public int getVolume() {
        return volume;
    }
    public void setVolume(int volume) {
        this.volume = volume;
    }
    public void addSong(String song, int length) {
        playlist.put(song,length);
    }
    public void removeSong(String song) {
        playlist.remove(song);
    }
    public HashMap<String,Integer> getPlaylist() {
        return playlist;
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
