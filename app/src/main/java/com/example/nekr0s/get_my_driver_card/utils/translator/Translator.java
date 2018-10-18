package com.example.nekr0s.get_my_driver_card.utils.translator;

import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// Translator - transliterates bulgarian cyrillic names to their latin equivalent.
// Based on The Law of Transliteration https://slovored.com/transliteration/rules.html
public class Translator {

    private final Map<Character, String> hashMap = new HashMap<Character, String>() {{
        put(' ', " ");
        put('а', "a");
        put('б', "b");
        put('в', "v");
        put('г', "g");
        put('д', "d");
        put('е', "e");
        put('ж', "zh");
        put('з', "z");
        put('и', "i");
        put('й', "y");
        put('к', "k");
        put('л', "l");
        put('м', "m");
        put('н', "n");
        put('о', "o");
        put('п', "p");
        put('р', "r");
        put('с', "s");
        put('т', "t");
        put('у', "u");
        put('ф', "f");
        put('х', "h");
        put('ц', "ts");
        put('ч', "ch");
        put('ш', "sh");
        put('щ', "sht");
        put('ъ', "a");
        put('ь', "y");
        put('ю', "yu");
        put('я', "ya");

    }};

    public Translator() {

    }

    public String translate(String name) {
        StringBuilder builder = new StringBuilder();
        String toCheck = name.toLowerCase();
        for (int i = 0; i < toCheck.length(); i++) {
            try {
                if (i == 0 || toCheck.charAt(i - 1) == ' ')
                    builder.append(formatComplexLetter(hashMap.get(toCheck.charAt(i))));
                else builder.append(hashMap.get(toCheck.charAt(i)));
            } catch (NullPointerException e) {
                return "Error";
            }
        }
//        return builder.substring(0, 1).toUpperCase() + builder.substring(1);
        return builder.toString();
    }

    @NonNull
    private String formatComplexLetter(String letter) {
        if (letter.length() > 1) {
            return letter.substring(0, 1).toUpperCase() +
                    letter.substring(1);
        } else {
            return letter.toUpperCase();
        }
    }
}
