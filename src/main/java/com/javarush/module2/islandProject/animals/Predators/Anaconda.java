package com.javarush.module2.islandProject.animals.Predators;

import com.javarush.module2.islandProject.entity.Island;
import com.javarush.module2.islandProject.entity.Predator;
import com.javarush.module2.islandProject.interfaces.Movable;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@ToString
public class Anaconda extends Predator implements Movable {
    public Anaconda() {
        super("Anaconda", 15.0, 30, 1, 50.0, 3.0, getDefaultEatProbability());
    }
    private static Map<String, Integer> getDefaultEatProbability() {
        Map<String, Integer> eatProbability = new HashMap<>();
        eatProbability.put("Mouse", 40);
        eatProbability.put("Fox", 15);
        eatProbability.put("Rabbit", 20);
        eatProbability.put("Duck", 10);
        return eatProbability;
    }

    @Override
    public void setMaxQuantityAtGrid(int maxQuantityAtGrid) {

    }

    @Override
    public void setWeight(double weight) {

    }

    @Override
    public void eat(Island island) {
        super.eat(island);
    }

    @Override
    public void die() {

    }
}
