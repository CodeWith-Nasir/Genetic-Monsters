package com.example.geneticmonsters;

import java.io.Serializable;

public class Monster implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String color;
    private int strength;
    private int speed;

    public Monster(String name, String color, int strength, int speed) {
        this.name = name;
        this.color = color;
        this.strength = strength;
        this.speed = speed;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public int getStrength() {
        return strength;
    }

    public int getSpeed() {
        return speed;
    }

    public String toString() {
        return String.format("Name: %s, Color: %s, Strength: %d, Speed: %d", name, color, strength, speed);
    }
}