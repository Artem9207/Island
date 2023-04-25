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
public class Eagle extends Predator implements Movable {
    public Eagle() {
        super("Eagle", 6.0, 20, 3, 50.0, 1.0, getDefaultEatProbability() );
    }

    private static Map<String, Integer> getDefaultEatProbability() {
        Map<String, Integer> eatProbability = new HashMap<>();
        eatProbability.put("Mouse", 90);
        eatProbability.put("Fox", 10);
        eatProbability.put("Rabbit", 90);
        eatProbability.put("Duck", 80);
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
