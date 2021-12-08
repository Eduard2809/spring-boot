package com.test.service;

import java.util.UUID;

public class GenerateString {

    public String generateString() {
        String uuid = UUID.randomUUID().toString();
        return uuid.substring(0,10);
    }
}
