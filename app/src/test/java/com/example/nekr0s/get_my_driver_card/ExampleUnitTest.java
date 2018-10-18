package com.example.nekr0s.get_my_driver_card;

import com.example.nekr0s.get_my_driver_card.utils.translator.Translator;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void Bulgarian_To_Latin_Should_Be_Correct() {
        Translator translator = new Translator();

        String input = "Яна Иванова";

        String result = "Yana Ivanova";

        assertEquals(translator.translate(input), result);
    }

    @Test
    public void Invalid_Input_Should_Return_Error_String() {
        Translator translator = new Translator();

        String input = "666";

        String result = "Error";

        assertEquals(translator.translate(input), result);
    }
}