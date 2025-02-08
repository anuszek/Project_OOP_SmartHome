package SmartSystem;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Fridge extends SmartDevice {
    private String deviceType = "Fridge";
    @JsonProperty
    private List<String> inventory = new ArrayList<>();
    @JsonProperty
    private boolean Skyrim = false;
    @JsonProperty
    private int fridgeTemp = 7;
    @JsonProperty
    private int freezerTemp = -5;

    public Fridge(){}

    public Fridge(String name, String description) {
        super(name, description);
    }

    public Fridge(String name, String description,boolean online, String deviceId,int fridgeTemp, int freezerTemp, List<String> inventory, boolean Skyrim) {
        super(name, description, online, deviceId);
        this.fridgeTemp = fridgeTemp;
        this.freezerTemp = freezerTemp;
        this.inventory = inventory;
        this.Skyrim = Skyrim;
    }
    public String getInventory() {
        return inventory.toString();
    }
    public void setInventory(List<String> inventory) {
        this.inventory = inventory;
    }
    public void add2Inventory(String item) {
        inventory.add(item);
    }
    public void removeFromInventory(String item) {
        inventory.remove(item);
    }
    public void isSkyrim() {
        if (Skyrim) {
            System.out.println("Skyrim :)");
        }
        else {
            System.out.println("No Skyrim :(\nIf you'd like to get Skyrim, please pay me 999$. :p");
        }
    }
    public List<String> getShoppingList(List<String> shoppingList) {
        List<String> list = new ArrayList<String>();
        for (String item : shoppingList) {
            if (!inventory.contains(item)) {
                list.add(item);
            }
        }
        return list;
    }
    public int getFridgeTemp() {
        return fridgeTemp;
    }
    public void setFridgeTemp(int fridgeTemp) {
        this.fridgeTemp = fridgeTemp;
    }
    public int getFreezerTemp() {
        return freezerTemp;
    }
    public void setFreezerTemp(int freezerTemp) {
        this.freezerTemp = freezerTemp;
    }

    @Override
    public String getDeviceType() {
        return deviceType;
    }
}
