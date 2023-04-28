package com.javarush.module2.islandProject.entity;

import lombok.ToString;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@ToString
public class Location {
    private int x;
    private int y;
    private ArrayList<GameObject> gameObjects;
    private ArrayList<Animal> animals;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
        this.gameObjects = new ArrayList<>();
        this.animals = new ArrayList<>();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public ArrayList<GameObject> getGameObjects() {
        return gameObjects;
    }


    public List<Animal> getAnimals() {
        return gameObjects.stream()
                .filter(gameObject -> gameObject instanceof Animal)
                .map(gameObject -> (Animal) gameObject)
                .collect(Collectors.toList());
    }

    public List<Animal> getAnimalsInLocation(Location location) {
        return gameObjects.stream()
                .filter(gameObject -> gameObject instanceof Animal && ((Animal) gameObject).getLocation() == location)
                .map(gameObject -> (Animal) gameObject)
                .collect(Collectors.toList());
    }



    public void addGameObject(GameObject gameObject) {
        gameObjects.add(gameObject);
    }

    public void removeGameObject(GameObject gameObject) {
        gameObjects.removeIf(obj -> obj.equals(gameObject));
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
        animal.setLocation(this);
    }

    public void removeAnimal(Animal animal) {
        animals.remove(animal);
        animal.setLocation(null);
    }

    public void removeDeadAnimals() {
        Iterator<Animal> it = animals.iterator();
        while (it.hasNext()) {
            Animal animal = it.next();
            if (!animal.isAlive()) {
                it.remove();
            }
        }
    }


}
