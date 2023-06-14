package com.example.aiui;

public class ThemaBeheerder {
    private static boolean darkMode;
    private static boolean lightMode;
    private static boolean colorMode1;
    private static boolean colorMode2;

    public static boolean isDarkMode() {
        return darkMode;
    }

    public static void setDarkMode(boolean value) {
        darkMode = value;
    }

    public static boolean isLightMode() {
        return lightMode;
    }

    public static void setLightMode(boolean value) {
        lightMode = value;
    }

    public static boolean isColorMode1() {
        return colorMode1;
    }

    public static void setColorMode1(boolean value) {
        colorMode1 = value;
    }

    public static boolean isColorMode2() {
        return colorMode2;
    }

    public static void setColorMode2(boolean value) {
        colorMode2 = value;
    }
}
