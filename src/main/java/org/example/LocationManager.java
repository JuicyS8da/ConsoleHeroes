package org.example;

import java.util.List;

public class LocationManager {

    public static Location getLocationById(int location_id) {
        switch (location_id) {
            case 1 -> {
                return new ForestLocation();
            }
            case 2 -> {
                return new MountainLocation();
            }
        }
        return null;
    }

}
