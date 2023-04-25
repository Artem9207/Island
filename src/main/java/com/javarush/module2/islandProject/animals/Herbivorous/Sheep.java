package com.javarush.module2.islandProject.animals.Herbivorous;

import com.javarush.module2.islandProject.entity.Herbivorous;
import com.javarush.module2.islandProject.entity.Island;
import com.javarush.module2.islandProject.entity.Location;
import com.javarush.module2.islandProject.interfaces.Movable;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@ToString
public class Sheep extends Herbivorous implements Movable {

    public Sheep() {
        super("Sheep", 70.0, 140, 3, 50.0, 15.0, getDefaultEatProbability());
    }

    private static Map<String, Integer> getDefaultEatProbability() {
        Map<String, Integer> eatProbability = new HashMap<>();
        eatProbability.put("Grass", 100);
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
    public void Die() {

    }
}
