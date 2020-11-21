package com.lielamar.betterwither.modules;

public class WitherStats {

    private int health;
    private double strength;
    private WitherSize size;

    public WitherStats(int health, double strength, WitherSize size) {
        this.health = health;
        this.strength = strength;
        this.size = size;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public double getStrength() {
        return strength;
    }

    public void setStrength(double strength) {
        this.strength = strength;
    }

    public WitherSize getSize() {
        return size;
    }

    public void setSize(WitherSize size) {
        this.size = size;
    }
}
