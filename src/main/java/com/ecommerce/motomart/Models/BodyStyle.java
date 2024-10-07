package com.ecommerce.motomart.Models;

public enum BodyStyle {
    CRUISER("Cruiser"),
    SPORTS("Sports"),
    SCOOTY("Scooty"),
    STREET("Street"),
    SUPER("Super");

    private final String displayName;

    BodyStyle(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
