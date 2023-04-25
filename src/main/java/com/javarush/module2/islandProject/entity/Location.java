package com.javarush.module2.islandProject.entity;

import lombok.ToString;

import java.util.ArrayList;
@ToString
public class Location {
    private int x;
    private int y;
    private ArrayList<GameObject> gameObjects;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
        this.gameObjects = new ArrayList<>();
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


    public ArrayList<Animal> getAnimals(ArrayList<GameObject> gameObjects) {
        ArrayList<Animal> animals = new ArrayList<>();

        for (GameObject gameObject : gameObjects) {
            if (gameObject instanceof Animal) {
                Animal animal = (Animal) gameObject;
                animals.add(animal);
            }
        }

        return animals;
    }

    public void addGameObject(GameObject gameObject) {
        gameObjects.add(gameObject);
    }

    public void removeGameObject(GameObject gameObject) {
        gameObjects.remove(gameObject);
    }

}
