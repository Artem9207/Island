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
public class Fox extends Predator implements Movable {

    public Fox() {
        super("Fox", 8.0, 30, 2, 50.0, 2.0, getDefaultEatProbability());
    }

    private static Map<String, Integer> getDefaultEatProbability() {
        Map<String, Integer> eatProbability = new HashMap<>();
        eatProbability.put("Mouse", 90);
        eatProbability.put("Rabbit", 70);
        eatProbability.put("Duck", 10);
        eatProbability.put("Caterpillar", 40);
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

    @Override
    public void move(int newX, int newY) {
        setX(newX);
        setY(newY);
    }
}
