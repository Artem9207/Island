package com.javarush.module2.islandProject.entity;

import com.javarush.module2.islandProject.interfaces.Eating;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor
public abstract class Herbivorous extends Animal {

    public Herbivorous(String name, double weight, int maxQuantityAtGrid, int speed, double satiety, double foodForFullSatiety, Map<String, Integer> eatProbability) {
        super(name, weight, maxQuantityAtGrid, speed, satiety, foodForFullSatiety, eatProbability);
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

    }
}
