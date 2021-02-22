package com.json.model;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class JsonObject extends JsonNode{
    private Map<String, JsonNode> entries;

    public JsonObject() {
        entries = new LinkedHashMap<String, JsonNode>() {

            @Override
            public String toString() {            //AbstractMap 543 стр
                Iterator<Map.Entry<String, JsonNode>> i = entrySet().iterator();
                if (! i.hasNext())
                    return "{}";

                StringBuilder sb = new StringBuilder();
                sb.append('{');
                for (;;) {
                    Map.Entry<String, JsonNode> e = i.next();
                    String key = e.getKey();
                    JsonNode value = e.getValue();
                    sb.append(key);
                    sb.append(':');  //замена = на :
                    sb.append(value);
                    if (!i.hasNext())
                        return sb.append('}').toString();
                    sb.append(',').append(' ');
                }
            }
        };
    }

    public Map<String, JsonNode> getMap() {
        return entries;
    }

    public void add(String key, JsonNode node) {
        entries.put(key, node);
    }

    @Override
    public String toString() {
        return entries.toString();
    }
}