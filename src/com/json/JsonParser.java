package com.json;

import com.json.exception.JsonParseException;
import com.json.model.JsonArray;
import com.json.model.JsonNode;
import com.json.model.JsonObject;
import com.json.model.JsonValue;

import static com.json.model.Token.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;

public final class JsonParser {
    private static Iterator<String> json;
    private static String token;

    private JsonParser() {
    }

    public static JsonNode parseFromFile(String jsonFilePath) {
        try {
            FileReader fileReader = new FileReader(jsonFilePath);
            StringBuilder builder = new StringBuilder();
            int symbol;
            while ((symbol = fileReader.read()) != -1) {
                builder.append((char) symbol);
            }
            return parseFromText(builder.toString());
        } catch (IOException e) {
            throw new JsonParseException(e);
        }
    }

    public static JsonNode parseFromText(String jsonText) {
        json = Arrays.asList(jsonText.replaceAll("\\s+(?=([^\"]*\"[^\"]*\")*[^\"]*$)", "")
                .split("(?<=[,\\[\\]{}:])|(?=[,\\[\\]{}:])"))
                .iterator();
        token = json.next();
        return parse();
    }

    private static JsonNode parse() {
        JsonNode result;
        switch (token) {
            case OBJECT_START:
                result = parseObject();
                break;
            case ARRAY_START:
                result = parseArray();
                break;
            default:
                result = parseValue();
        }
        return result;
    }

    private static JsonObject parseObject() {
        JsonObject result = new JsonObject();
        while (!token.equals(OBJECT_END)) {
            String key;
            JsonNode value;
            if ((token = json.next()).matches(STRING)) {
                key = token;
            } else if (token.equals(OBJECT_END)) {
                return result;
            } else {
                throw new JsonParseException("Unexpected token: " + token);
            }
            if (!(token = json.next()).equals(COLON)) {
                throw new JsonParseException("Unexpected token: " + token);
            }
            token = json.next();
            value = parse();
            result.add(key, value);
            token = json.next();
            if (!token.equals(COMMA) && !token.equals(OBJECT_END)) {
                throw new JsonParseException("Unexpected token: " + token);
            }
        }
        return result;
    }

    private static JsonArray parseArray() {
        JsonArray result = new JsonArray();
        while (!token.equals(ARRAY_END)) {
            token = json.next();
            if (token.equals(ARRAY_END)) {
                return result;
            }
            result.add(parse());
            token = json.next();
            if (!token.equals(COMMA) && !token.equals(ARRAY_END)) {
                throw new JsonParseException("Unexpected token: " + token);
            }
        }
        return result;
    }

    private static JsonValue parseValue() {
        if (token.matches(STRING)) {
            return new JsonValue(token.substring(1, token.length() - 1));
        }
        if (token.matches(NUMBER)) {
            try {
                return new JsonValue(Long.parseLong(token));
            } catch (NumberFormatException nfe) {
                return new JsonValue(Double.parseDouble(token));
            }
        }
        if (token.equals(TRUE) || token.equals(FALSE)) {
            return new JsonValue(Boolean.parseBoolean(token));
        }
        if (token.equals(NULL)) {
            return new JsonValue(null);
        }
        throw new JsonParseException("Unexpected token: " + token);
    }
}