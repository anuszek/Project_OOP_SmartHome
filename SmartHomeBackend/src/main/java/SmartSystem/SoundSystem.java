package SmartSystem;

import java.util.HashMap;

public class SoundSystem extends SmartDevice{
    private HashMap<String,Integer> playlist = new HashMap<>();
    private String deviceType = "SoundSystem";
    private int volume;

    public SoundSystem(){}
    public SoundSystem(String deviceName, String deviceId, String description,boolean online, int volume) {
        super(deviceName, deviceId, description,online);
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
    public String getPlaylist() {
        return playlist.toString();
    }

    @Override
    public String getDeviceType() {
        return deviceType;
    }
}
