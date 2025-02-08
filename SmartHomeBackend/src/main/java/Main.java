import SmartSystem.*;
import SmartSystem.JsonUtil;
import SmartSystem.DatabaseUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println(DbDevicesInterface.countDevices());
//        Locks lock = new Locks("Front Door", "Main entrance", true, false);
//        try {
//            DbDevicesInterface.insertDevice(JsonUtil.serialize(lock));
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
    }

}