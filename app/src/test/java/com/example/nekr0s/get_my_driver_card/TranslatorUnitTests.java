package com.example.nekr0s.get_my_driver_card;

import com.example.nekr0s.get_my_driver_card.utils.translator.Translator;

import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

/**
 * Tests dedicated strictly to the Translator class
 */
public class TranslatorUnitTests {

    @Test
    public void Bulgarian_To_Latin_Should_Be_Correct() throws FileNotFoundException {
        Translator translator = new Translator();

        String input = "Яна Иванова";

        String result = "Yana Ivanova";

        assertEquals(result, translator.translate(input));
    }

    @Test
    public void Input_WithInvalidSymbols_ShouldSkip_Transliterate_InvalidSymbols() throws FileNotFoundException {
        Translator translator = new Translator();

        String input = "666Александър$$$";

        String actual = translator.translate(input);

        String result = "666Aleksandar$$$";

        assertEquals(result, actual);
    }

    @Test
    public void Test_If_FileDirectory_IsCorrect() throws FileNotFoundException {
        // Check path
        System.out.println("Directory: " + System.getProperty("user.dir"));

        Translator translator = new Translator();
        System.out.println(translator.getObject().toString());
    }
}