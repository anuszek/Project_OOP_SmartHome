package SmartSystem;

import java.util.ArrayList;
import java.util.List;

public class Fridge extends SmartDevice {
    private List<String> inventory;
    private boolean Skyrim = false;
    private int fridgeTemp;
    private int freezerTemp;
    public Fridge(String name, String deviceId, String description) {
        super(name, deviceId, description);
    }
    public List<String> getInventory() {
        return inventory;
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
}
