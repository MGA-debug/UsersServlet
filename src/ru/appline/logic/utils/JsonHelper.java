package ru.appline.logic.utils;

import com.google.gson.JsonObject;

public class JsonHelper {

    private static JsonObject jsonMessage;

    private JsonHelper() {

    }

    public static JsonObject getJsonErrorMessage(String message) {
        if (jsonMessage == null)
            jsonMessage = new JsonObject();
        jsonMessage.addProperty("message", message);
        return jsonMessage;
    }
}
