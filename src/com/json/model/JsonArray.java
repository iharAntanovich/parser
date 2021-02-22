package com.json.model;

import java.util.ArrayList;
import java.util.List;

public class JsonArray extends JsonNode {
    private List<JsonNode> nodes;

    public JsonArray() {
        nodes = new ArrayList<>();
    }

    public List<JsonNode> getArray() {
        return nodes;
    }

    public void add(JsonNode node) {
        nodes.add(node);
    }

    @Override
    public String toString() {
        return nodes.toString();
    }
}