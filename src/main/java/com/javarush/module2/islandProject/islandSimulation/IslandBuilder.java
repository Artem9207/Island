package com.javarush.module2.islandProject.islandSimulation;

import com.javarush.module2.islandProject.animals.Herbivorous.*;
import com.javarush.module2.islandProject.animals.Predators.*;
import com.javarush.module2.islandProject.entity.*;

public class IslandBuilder {

    public void islandBuild(Island island) {
        Grass grass = new Grass();
        Boar boar = new Boar();
        Anaconda anaconda = new Anaconda();
        Wolf wolf = new Wolf();
        Horse horse = new Horse();
        Bear bear = new Bear();
        Eagle eagle = new Eagle();
        Fox fox = new Fox();
        Buffalo buffalo = new Buffalo();
        Deer deer = new Deer();
        Duck duck = new Duck();
        Goat goat = new Goat();
        Mouse mouse = new Mouse();
        Rabbit rabbit = new Rabbit();
        Sheep sheep = new Sheep();

        for (int i = 0; i < 10; i++) {
            island.addGameObject(anaconda, 1,5);
            island.addGameObject(horse, 2,5);
            island.addGameObject(eagle, 4,9);
            island.addGameObject(fox, 8,7);
            island.addGameObject(deer, 5, 3);
            island.addGameObject(goat, 1,9);
        }

        for (int i = 0; i < 5; i++) {
            island.addGameObject(boar, 1,3);
            island.addGameObject(wolf, 1,4);
            island.addGameObject(buffalo, 3,9);
            island.addGameObject(deer, 9, 3);
        }

        for (int i = 0; i < 15; i++) {
            island.addGameObject(duck, 5,5);
            island.addGameObject(rabbit, 3,2);
            island.addGameObject(sheep, 6,4);
        }

        for (int i = 0; i < 100; i++) {
            island.addGameObject(grass, 1,4);
            island.addGameObject(grass, 4,4);
            island.addGameObject(grass, 8,8);
            island.addGameObject(grass, 2,4);
            island.addGameObject(grass, 5,4);
            island.addGameObject(grass, 7,8);
            island.addGameObject(mouse, 7, 4);
        }

        for (int i = 0; i < 2; i++) {
            island.addGameObject(bear, 5, 3);
        }
    }

    public void start(Island island, int days) {
        for (int i = 0; i < days; i++) {
            island.moveAllAnimals();
            island.timeToReproduce();
            island.timeToEat();
//            island.check();
            island.moveAllAnimals();
            island.timeToEat();

            island.endOfDay();
            System.out.println(island.getGameObjects());
        }
    }
}
