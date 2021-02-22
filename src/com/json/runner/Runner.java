package com.json.runner;

import com.json.JsonParser;
import com.json.model.JsonNode;

import java.io.FileNotFoundException;

public class Runner {
    public static void main(String[] args) throws FileNotFoundException {
        JsonNode node = JsonParser.parseFromFile("src/com/json/example.json");
        System.out.println(node);
    }
}