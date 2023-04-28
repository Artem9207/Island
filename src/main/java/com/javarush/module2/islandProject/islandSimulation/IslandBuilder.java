package com.javarush.module2.islandProject.islandSimulation;

import com.javarush.module2.islandProject.animals.Herbivorous.*;
import com.javarush.module2.islandProject.animals.Predators.*;
import com.javarush.module2.islandProject.entity.*;

public class IslandBuilder {

    public void islandBuild(Island island) {



        for (int i = 0; i < 1; i++) {
            island.addGameObject(new Anaconda(), 1,5);
            island.addGameObject(new Horse(), 2,5);
            island.addGameObject(new Eagle(), 4,9);
            island.addGameObject(new Fox(), 8,7);
            island.addGameObject(new Deer(), 5, 3);
            island.addGameObject(new Goat(), 1,9);
        }

        for (int i = 0; i < 5; i++) {
            island.addGameObject(new Boar(), 1,3);
            island.addGameObject(new Wolf(), 1,4);
            island.addGameObject(new Buffalo(), 3,9);
        }

        for (int i = 0; i < 15; i++) {
            island.addGameObject(new Duck(), 5,5);
            island.addGameObject(new Rabbit(), 3,2);
            island.addGameObject(new Sheep(), 6,4);
            island.addGameObject(new Mouse(), 7, 4);
        }

        for (int i = 0; i < 100; i++) {
            island.addGameObject(new Grass(), 1,4);
            island.addGameObject(new Grass(), 4,4);
            island.addGameObject(new Grass(), 8,8);
            island.addGameObject(new Grass(), 2,4);
            island.addGameObject(new Grass(), 5,4);
            island.addGameObject(new Grass(), 7,8);
        }

        for (int i = 0; i < 1; i++) {
            island.addGameObject(new Bear() , 5, 3);
        }


    }

    public void start(Island island, int days) {
        int daysGone = island.getDaysGone();
        for (int i = 0; i < days; i++) {
            island.setDaysGone(daysGone++);
            island.endOfDay();
            island.moveAllAnimals();
            island.timeToEat(island);
            island.moveAllAnimals();
            island.timeToEat(island);
            island.timeToReproduce();
        }
    }
}
