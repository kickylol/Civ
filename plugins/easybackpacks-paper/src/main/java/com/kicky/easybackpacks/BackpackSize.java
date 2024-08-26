package com.kicky.easybackpacks;

public enum BackpackSize {
    SMALL(9, "Backpack"),
    MEDIUM(18, "Backpack"),
    LARGE(27, "Backpack");

    private final int value;
    private final String type;

    BackpackSize(int value, String type) {
        this.value = value;
        this.type = type;
    }

    public int toInt() {
        return value;
    }

    public String getSize() {
        return type;
    }

    public static BackpackSize valueOf(Integer value) {
        if (value == null) {
            return null;
        }

        return switch (value) {
            case 9 -> SMALL;
            case 18 -> MEDIUM;
            case 27 -> LARGE;
            default -> null;
        };

    }

    public static BackpackSize fromString(String type) {
        if (type == null) {
            return null;
        }

        return switch (type) {
            case "small" -> SMALL;
            case "medium" -> MEDIUM;
            case "large" -> LARGE;
            default -> null;
        };

    }
}
