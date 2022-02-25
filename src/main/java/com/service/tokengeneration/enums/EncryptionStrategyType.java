package com.service.tokengeneration.enums;

public enum EncryptionStrategyType {
    PRIVATE_KEY("PRIVATE_KEY"), BASE64("BASE64");

    private String name;

    EncryptionStrategyType(String value) {
        name = value;
    }

    public String getName() {
        return name;
    }
}
