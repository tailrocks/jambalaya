package com.jambalaya.example.dto;

/**
 * @author Alexey Zhokhov
 */
public class SimpleAccountUpdaterLog {

    private final String message;

    public SimpleAccountUpdaterLog(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
