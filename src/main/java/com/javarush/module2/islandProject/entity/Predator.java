package com.javarush.module2.islandProject.entity;

import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Map;

@NoArgsConstructor
@ToString
public abstract class Predator extends Animal {

    public Predator(String name, double weight, int maxQuantityAtGrid, int speed, double satiety, double foodForFullSatiety, Map<String, Integer> eatProbability) {
        super(name, weight, maxQuantityAtGrid, speed, satiety, foodForFullSatiety, eatProbability);
    }

    @Override
    public void eat(Island island) {
        super.eat(island);
    }

    @Override
    public void die() {

    }

    @Override
    public void move(int newX, int newY) {

    }
}
