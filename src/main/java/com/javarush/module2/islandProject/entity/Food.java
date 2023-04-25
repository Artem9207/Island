package com.javarush.module2.islandProject.entity;

public abstract class Food {
    private String name;
    private double weight;

    public Food(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }
}
