package com.ecommerce.motomart.Models;

public enum AccessoryCategory {
    BIKE_PARTS("Bike Parts"),
    RIDING_GEARS("Riding Gears"),
    LUGGAGE_AND_TOURING("Luggage and Touring"),
    HELMETS_AND_COMBOS("Helmets and Combos");

    private final String displayName;

    AccessoryCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
