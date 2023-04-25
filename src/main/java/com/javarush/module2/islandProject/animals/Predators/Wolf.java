package com.javarush.module2.islandProject.animals.Predators;

import com.javarush.module2.islandProject.entity.Island;
import com.javarush.module2.islandProject.entity.Location;
import com.javarush.module2.islandProject.entity.Predator;
import com.javarush.module2.islandProject.interfaces.Movable;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;


@ToString
public class Wolf extends Predator implements Movable {
    public Wolf() {
        super("Wolf", 50.0, 30, 2, 50.0, 8.0, getDefaultEatProbability() );
    }

    private static Map<String, Integer> getDefaultEatProbability() {
        Map<String, Integer> eatProbability = new HashMap<>();
        eatProbability.put("Mouse", 80);
        eatProbability.put("Rabbit", 60);
        eatProbability.put("Duck", 40);
        eatProbability.put("Horse", 10);
        eatProbability.put("Deer", 15);
        eatProbability.put("Goat", 60);
        eatProbability.put("Sheep", 70);
        eatProbability.put("Boar", 15);
        eatProbability.put("Buffalo", 10);
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
