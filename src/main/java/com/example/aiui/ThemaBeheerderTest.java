package com.example.aiui;

import org.junit.Test;


import static org.junit.Assert.assertEquals;

public class ThemaBeheerderTest {

    @Test
    public void testDarkMode() {
        ThemaBeheerder.setDarkMode(true);
        ThemaBeheerder.setLightMode(false);
        ThemaBeheerder.setColorMode1(false);
        ThemaBeheerder.setColorMode2(false);

        // Assert the expected styles for dark mode
        assertEquals("black", achtergrond.getStyle());
        assertEquals("black; -fx-border-radius: 24px", loginpanel.getStyle());
        assertEquals("white", titel.getStyle());
        assertEquals("black", button.getStyle());
    }

    // Voeg hier meer testmethoden toe voor andere themamodi

}
