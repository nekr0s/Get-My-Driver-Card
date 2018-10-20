package com.example.nekr0s.get_my_driver_card.utils.translator;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// Translator - transliterates bulgarian cyrillic names to their latin equivalent.
// Based on The Law of Transliteration https://slovored.com/transliteration/rules.html
public class Translator {

    private JsonObject object;

    public Translator() throws Exception {
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(new FileInputStream(
                        "src/main/assets/charset.json"),
                        "windows-1251"));
        Gson gson = new Gson();
        object = gson.fromJson(bufferedReader, JsonObject.class);
    }

    public String translate(String name) {
        StringBuilder builder = new StringBuilder();
        String upperCaseName = name.toUpperCase();
        for (int i = 0; i < name.length(); i++) {
            if (object.get(String.valueOf(name.charAt(i))) == null) {
                builder.append(formatLetter(
                        object.get(String.valueOf(upperCaseName.charAt(i))), name, i));
            } else {
                builder.append(object.get(String.valueOf(name.charAt(i))).getAsString());
            }
        }
        return builder.toString();
    }

    @NonNull
    private String formatLetter(JsonElement jsonElement, String name, int i) {
        if (jsonElement == null) return String.valueOf(name.charAt(i));
        String letter = jsonElement.getAsString();
        if (i > 0 && name.charAt(i - 1) != ' ') return letter.toLowerCase();
        return letter;
    }

    public JsonObject getObject() {
        return object;
    }
}
