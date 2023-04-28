package com.javarush.module2.islandProject.animals.Herbivorous;

import com.javarush.module2.islandProject.entity.Herbivorous;
import com.javarush.module2.islandProject.entity.Island;
import com.javarush.module2.islandProject.interfaces.Movable;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;


@ToString
public class Mouse extends Herbivorous implements Movable {

    public Mouse() {
        super("Mouse", 0.05, 500, 1, 50.0, 0.01, getDefaultEatProbability());
    }

    private static Map<String, Integer> getDefaultEatProbability() {
        Map<String, Integer> eatProbability = new HashMap<>();
        eatProbability.put("Grass", 100);
        eatProbability.put("Caterpillar", 90);
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
