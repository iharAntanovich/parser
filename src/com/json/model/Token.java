package com.json.model;

public interface Token {
    String OBJECT_START = "{";
    String OBJECT_END = "}";
    String ARRAY_START = "[";
    String ARRAY_END = "]";
    String COMMA = ",";
    String COLON = ":";
    String TRUE = "true";
    String FALSE = "false";
    String NULL = "null";
    String STRING = "\"([^\"]+)\"";   // ругулярные выражения любые символы между двойными кавычками
    String NUMBER = "-?\\d+(\\.\\d*)?";   //число (регулярное выражение числа)
}