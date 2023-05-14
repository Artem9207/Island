package com.javarush.module2.islandProject.entity;

import lombok.ToString;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@ToString
public class Location {
    private final int x;
    private final int y;
    private final List<GameObject> gameObjects;
    private final List<Animal> animals;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
        this.gameObjects = new ArrayList<>();
        this.animals = new ArrayList<>();
    }

    public List<GameObject> getGameObjects() {
        return new ArrayList<>(gameObjects);
    }

    public List<Animal> getAnimals() {
        synchronized (animals) {
            return new ArrayList<>(animals);
        }
    }

    public List<Animal> getAnimalsInLocation(Location location, Island island) {
        synchronized (island.getAnimals()) {
            List<Animal> animalsInLocation = new ArrayList<>(island.getAnimals());
            return animalsInLocation.stream()
                    .filter(animal -> animal != null && animal.getLocation() == location)
                    .collect(Collectors.toList());
        }
    }


    public List<GameObject> getGameObjectsInLocation(Location location, Island island) {
        synchronized (island.getGameObjects()) {
            List<GameObject> gameObjectsInLocation = new ArrayList<>(island.getGameObjects());
            return gameObjectsInLocation.stream()
                    .filter(gameObject -> gameObject.getLocation() == location)
                    .collect(Collectors.toList());
        }
    }


    public void addGameObject(GameObject gameObject) {
        gameObjects.add(gameObject);
    }

    public void removeGameObject(GameObject gameObject) {
        gameObjects.remove(gameObject);
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
        animal.setLocation(this);
    }

    public void removeAnimal(Animal animal) {
        animals.remove(animal);
        animal.setLocation(null);
    }
}