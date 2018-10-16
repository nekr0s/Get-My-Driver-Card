package com.example.nekr0s.get_my_driver_card.models.translator;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// Beta version
public class Translator {

    private final Map<Character, String> hashMap = new HashMap<Character, String>() {{
        put(' ', " ");
        put('а', "a");
        put('б', "b");
        put('в', "v");
        put('г', "g");
        put('д', "d");
        put('е', "e");
        put('ж', "j");
        put('з', "z");
        put('и', "i");
        put('й', "i");
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
        put('ц', "c");
        put('ч', "ch");
        put('ш', "sh");
        put('щ', "sht");
        put('ъ', "a");
        put('ь', "y");
        put('я', "ya");

    }};
    private final int size = 32;

    public Translator() {

    }

    public String translate(String name) {
        StringBuilder builder = new StringBuilder();
        String toCheck = name.toLowerCase();
        for (int i = 0; i < toCheck.length(); i++) {
            if (i == 0)
                builder.append(Objects.requireNonNull(hashMap.get(toCheck.charAt(i))).toUpperCase());
            else
                builder.append(hashMap.get(toCheck.charAt(i)));
        }
        return builder.toString();
    }
}
