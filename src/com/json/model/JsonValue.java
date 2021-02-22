package com.json.model;

public class JsonValue extends JsonNode {
    private Object value;

    public JsonValue(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public boolean isString() {
        return value instanceof String;
    }

    public boolean isLong() {
        return value instanceof Integer;
    }

    public boolean isDouble() {
        return value instanceof Double;
    }

    public boolean isNull() {
        return value == null;
    }

    public boolean isBoolean() {
        return value instanceof Boolean;
    }

    public String getAsString() {
        if (isString()) {
            return (String) value;
        }
        throw new IllegalStateException("Not a String");
    }

    public Long getAsLong() {
        if (isLong()) {
            return (Long) value;
        }
        throw new IllegalStateException("Not a Long");
    }

    public Double getAsDouble() {
        if (isDouble()) {
            return (Double) value;
        }
        throw new IllegalStateException("Not a Double");
    }

    public Boolean getAsBoolean() {
        if (isBoolean()) {
            return (Boolean) value;
        }
        throw new IllegalStateException("Not a Boolean");
    }

    public <T> T getAsNull() {
        if (isNull()) {
            return null;
        }
        throw new IllegalStateException("Not a null");
    }

    @Override
    public String toString() {
        return value == null ? null : isString() ? "\"" + value + "\"" : value.toString();
    }
}