package com.example.nekr0s.get_my_driver_card;

import com.example.nekr0s.get_my_driver_card.utils.translator.Translator;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests dedicated strictly to the Translator class
 */
public class TranslatorUnitTests {

    @Test
    public void Bulgarian_To_Latin_Should_Be_Correct() {
        Translator translator = new Translator();

        String input = "Яна Иванова";

        String result = "Yana Ivanova";

        assertEquals(result, translator.translate(input));
    }

    @Test
    public void Invalid_Input_ShouldNot_Transliterate_InvalidSymbols() {
        Translator translator = new Translator();

        String input = "666Александър$$$";

        String actual = translator.translate(input);

        String result = "666Aleksandar$$$";

        assertEquals(result, actual);
    }
}