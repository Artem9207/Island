package com.javarush.module2.islandProject.animals.Herbivorous;

import com.javarush.module2.islandProject.entity.Herbivorous;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@ToString
public class Caterpillar extends Herbivorous {
    public Caterpillar() {
        super("Caterpillar", 0.01, 1000, 0, 100.0, 0.0, getDefaultEatProbability());
    }

    private static Map<String, Integer> getDefaultEatProbability() {
        Map<String, Integer> eatProbability = new HashMap<>();
        return eatProbability;
    }

    @Override
    public void setMaxQuantityAtGrid(int maxQuantityAtGrid) {

    }

    @Override
    public void setWeight(double weight) {

    }

    @Override
    public void die() {
    }
}
