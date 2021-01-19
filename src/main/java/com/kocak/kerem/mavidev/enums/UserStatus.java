package com.kocak.kerem.mavidev.enums;

public enum UserStatus {

    AKTIF(1),
    PASIF(2);

    private final int status;

    UserStatus(final int status) {
        this.status = status;
    }

    public int getStatus() {
        return this.status;
    }
}
