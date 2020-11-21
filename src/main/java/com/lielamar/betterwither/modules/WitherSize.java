package com.lielamar.betterwither.modules;

public enum WitherSize {

    SMALL(700),
    NORMAL(500),
    LARGE(2000),
    EXTRA_LARGE(3000);

    private final int sizeValue;

    WitherSize(int sizeValue) {
        this.sizeValue = sizeValue;
    }

    public int getSizeValue() {
        return sizeValue;
    }
}
