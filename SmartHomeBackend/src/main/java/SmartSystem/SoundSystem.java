package SmartSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SoundSystem extends SmartDevice{
    private List<String> playlist = new ArrayList<>();
    private String deviceType = "SoundSystem";
    private int volume = 50;

    public SoundSystem(){}
    public SoundSystem(String deviceName, String description) {
        super(deviceName, description);
        this.volume = 50;
    }
    public int getVolume() {
        return volume;
    }
    public void setVolume(int volume) {
        this.volume = volume;
    }
    public void addSong(String song) {
        playlist.add(song);
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
