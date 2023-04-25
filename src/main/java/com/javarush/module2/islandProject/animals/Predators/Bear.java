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
public class Bear extends Predator implements Movable {
    public Bear() {
        super("Bear", 500.0, 5, 2, 50.0, 80.0, getDefaultEatProbability());
    }

    private static Map<String, Integer> getDefaultEatProbability() {
        Map<String, Integer> eatProbability = new HashMap<>();
        eatProbability.put("Mouse", 50);
        eatProbability.put("Anaconda", 80);
        eatProbability.put("Rabbit", 90);
        eatProbability.put("Duck", 10);
        eatProbability.put("Horse", 40);
        eatProbability.put("Deer", 80);
        eatProbability.put("Goat", 70);
        eatProbability.put("Sheep", 70);
        eatProbability.put("Boar", 50);
        eatProbability.put("Buffalo", 20);
        return eatProbability;
    }

    @Override
    public void setName(String name) {
        this.setName(name);
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

    @Override
    public void move(int newX, int newY) {
        setX(newX);
        setY(newY);
    }
}
