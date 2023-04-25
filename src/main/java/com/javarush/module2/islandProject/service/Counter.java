package com.javarush.module2.islandProject.service;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
@Getter
@Setter
public class Counter {
    private int animalsEaten;
    private int animalsBorn;
    private int animalsDied;
    private Map<String, Integer> animalsOnIsland;

    public Counter() {
        this.animalsOnIsland = new HashMap<>();
    }

    public void incrementAnimalsEaten() {
        animalsEaten++;
    }

    public int getAnimalsEaten() {
        return animalsEaten;
    }

    public void incrementAnimalsBorn() {
        animalsBorn++;
    }

    public int getAnimalsBorn() {
        return animalsBorn;
    }

    public void incrementAnimalsDied() {
        animalsDied++;
    }

    public int getAnimalsDied() {
        return animalsDied;
    }

    public void incrementAnimalsOnIsland(String animalClass) {
        animalsOnIsland.put(animalClass, animalsOnIsland.getOrDefault(animalClass, 0) + 1);
    }

    public void decrementAnimalsOnIsland(String animalClass) {
        animalsOnIsland.put(animalClass, animalsOnIsland.getOrDefault(animalClass, 0) - 1);
    }

    public int getAnimalsOnIsland(String animalClass) {
        return animalsOnIsland.getOrDefault(animalClass, 0);
    }
}
