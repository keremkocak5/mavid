package com.kocak.kerem.linkconverter.enums;

public enum MatchType {

    UNCLASSIFIED(0),
    PRODUCT(1),
    SEARCH(2);

    private final int type;

    MatchType(final int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
