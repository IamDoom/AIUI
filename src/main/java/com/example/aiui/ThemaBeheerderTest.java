package com.example.aiui;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ThemaBeheerderTest {

    @Test
    public void testTypeModeDarkTrue() {
        // Testgegevens
        boolean darkMode = true;
        boolean lightMode = false;
        boolean theme1 = false;
        boolean theme2 = false;
        boolean expectedResult = true;

        // Uitvoeren van de functie
        boolean actualResult = calculateTypeModeDarkTrue(darkMode, lightMode, theme1, theme2);

        // Vergelijken van het verwachte resultaat met het daadwerkelijke resultaat
        assertEquals(expectedResult, actualResult);
    }

    // Functie voor het berekenen van de Type Mode
    private boolean calculateTypeModeDarkTrue(boolean darkMode, boolean lightMode, boolean theme1, boolean theme2) {
        boolean result = darkMode ^ lightMode ^ theme1 ^ theme2;
        return result;
    }

    @Test
    public void testTypeModeFalse() {
        // Testgegevens
        boolean darkMode = false;
        boolean lightMode = false;
        boolean theme1 = false;
        boolean theme2 = false;
        boolean expectedResult = false;

        // Uitvoeren van de functie
        boolean actualResult = calculateTypeModeFalse(darkMode, lightMode, theme1, theme2);

        // Vergelijken van het verwachte resultaat met het daadwerkelijke resultaat
        assertEquals(expectedResult, actualResult);
    }

    // Functie voor het berekenen van de Type Mode
    private boolean calculateTypeModeFalse(boolean darkMode, boolean lightMode, boolean theme1, boolean theme2) {
        boolean result = darkMode ^ lightMode ^ theme1 ^ theme2;
        return result;
    }
    @Test
    public void testTypeModeThema2True() {
        // Testgegevens
        boolean darkMode = false;
        boolean lightMode = false;
        boolean theme1 = false;
        boolean theme2 = true;
        boolean expectedResult = true;

        // Uitvoeren van de functie
        boolean actualResult = calculateTypeModeThema2True(darkMode, lightMode, theme1, theme2);

        // Vergelijken van het verwachte resultaat met het daadwerkelijke resultaat
        assertEquals(expectedResult, actualResult);
    }

    // Functie voor het berekenen van de Type Mode
    private boolean calculateTypeModeThema2True(boolean darkMode, boolean lightMode, boolean theme1, boolean theme2) {
        boolean result = darkMode ^ lightMode ^ theme1 ^ theme2;
        return result;
    }




}