package com.json.model;

public abstract class JsonNode {
    public boolean isJsonObject() {
        return this instanceof JsonObject;
    }

    public boolean isJsonArray() {
        return this instanceof JsonArray;
    }

    public boolean isJsonValue() {
        return this instanceof JsonValue;
    }

    public JsonObject getAsJsonObject() {
        if (isJsonObject()) {
            return (JsonObject) this;
        }
        throw new IllegalStateException("Not a JsonObject");
    }

    public JsonArray getAsJsonArray() {
        if (isJsonArray()) {
            return (JsonArray) this;
        }
        throw new IllegalStateException("Not a JsonArray");
    }

    public JsonValue getAsJsonValue() {
        if (isJsonValue()) {
            return (JsonValue) this;
        }
        throw new IllegalStateException("Not a JsonValue");
    }
}