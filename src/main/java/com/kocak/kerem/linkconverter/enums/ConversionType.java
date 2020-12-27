package com.kocak.kerem.linkconverter.enums;

public enum ConversionType {

    TO_URL(1),
    TO_DEEPLINK(2);

    private final int type;

    ConversionType(final int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
