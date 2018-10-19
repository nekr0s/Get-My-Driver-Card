package com.example.nekr0s.get_my_driver_card.utils.translator;

import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

// Translator - transliterates bulgarian cyrillic names to their latin equivalent.
// Based on The Law of Transliteration https://slovored.com/transliteration/rules.html
public class Translator {

    private final Map<Character, String> hashMap = new HashMap<Character, String>() {{
        put(' ', " ");
        put('А', "A");
        put('Б', "B");
        put('В', "V");
        put('Г', "G");
        put('Д', "D");
        put('Е', "E");
        put('Ж', "Zh");
        put('З', "Z");
        put('И', "I");
        put('Й', "I");
        put('К', "K");
        put('Л', "L");
        put('М', "M");
        put('Н', "N");
        put('О', "O");
        put('П', "P");
        put('Р', "R");
        put('С', "S");
        put('Т', "T");
        put('У', "U");
        put('Ф', "F");
        put('Х', "H");
        put('Ц', "Ts");
        put('Ч', "Ch");
        put('Ш', "Sh");
        put('Щ', "Sht");
        put('Ъ', "A");
        put('Ь', "Y");
        put('Ю', "Yu");
        put('Я', "Ya");

    }};

    public Translator() {

    }

    public String translate(String name) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < name.length(); i++) {
            try {
                builder.append(formatLetter(hashMap.get(name.toUpperCase().charAt(i)), name, i));
            } catch (NullPointerException e) {
                return "Invalid symbols detected.";
            }
        }
        return builder.toString();
    }

    @NonNull
    private String formatLetter(String letter, String name, int i) {
        if (i > 0 && name.charAt(i - 1) != ' ') return letter.toLowerCase();
        return letter;
    }
}
