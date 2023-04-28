package com.javarush.module2.islandProject.entity;

import com.javarush.module2.islandProject.interfaces.Movable;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Island {
    private Location[][] locations;
    private ArrayList<GameObject> gameObjects;
    private ArrayList<Animal> animals;
    private List<Animal> deadAnimals = new ArrayList<>();
    private int animalsCount = 0;
    private int eatenAnimals = 0;
    private int birthsCount = 0;
    private int deathsCount = 0;
    private int starvingDeaths = 0;
    private int daysGone = 1;

    public Island() {
        int x = 20;
        int y = 20;
        locations = new Location[x][y];
        gameObjects = new ArrayList<>();
        animals = new ArrayList<>();

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                locations[i][j] = new Location(i, j);
            }
        }

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                addGameObject(new Grass(), i, j);
            }
        }
    }

    public void addGameObject(GameObject gameObject, int x, int y) {
        if (x < 0 || x >= locations.length || y < 0 || y >= locations[0].length) {
            throw new IllegalArgumentException("Некорректные координаты локации");
        }
        Location location = locations[x][y];
        location.addGameObject(gameObject);
        gameObject.setX(x);
        gameObject.setY(y);
        gameObject.setLocation(location);
        gameObjects.add(gameObject);
        if (gameObject instanceof Animal) {
            animals.add((Animal) gameObject);
            animalsCount++;
        }
    }

    public void removeGameObjectFromIsland(GameObject gameObject) {
        Island island = this;
        Location[][] locations = island.getLocations();
        if (gameObject instanceof Animal animal) {
            if (!animal.isAlive()) {
                deadAnimals.add(animal);
                deathsCount++;
            }
        }
        if (locations != null) {
            for (Location[] row : locations) {
                for (Location location : row) {
                    if (location.getGameObjects().contains(gameObject)) {
                        location.removeGameObject(gameObject);
                        gameObject.setLocation(null);
                        break;
                    }
                }
            }
        }
        island.getGameObjects().remove(gameObject);
        island.getAnimals().remove(gameObject);
    }

    public void moveAllAnimals() {
        Island island = this;

        List<GameObject> gameObjects = getGameObjects();
        for (GameObject gameObject : gameObjects) {
            if (gameObject instanceof Animal animal) {
                if (!animal.isAlive()) {
                    continue;
                }
                island.moveAnimal(animal);
            }
        }
    }

    public void moveAnimal(Movable movable) {

        int currentX = movable.getX();
        int currentY = movable.getY();
        int speed = movable.getSpeed();

        int newX = currentX + (int) (Math.random() * 3) - speed;
        int newY = currentY + (int) (Math.random() * 3) - speed;

        if (newX < 0 || newX >= locations.length || newY < 0 || newY >= locations[0].length) {
//            System.out.println(movable + " stays in place.");
        } else {
            movable.move(newX, newY);
//            System.out.println(movable + " moves to " + newX + ", " + newY);
            locations[currentX][currentY].removeAnimal((Animal) movable);
            locations[newX][newY].addAnimal((Animal) movable);
            ((Animal) movable).setLocation(locations[newX][newY]);
            movable.move(newX, newY);
//            System.out.println(((GameObject) movable).getName() + "Animal move from (" + currentX + "," + currentY + ") to (" + newX + "," + newY + ")");
        }
    }

    public void timeToEat(Island island) {
        for (int i = 0; i < gameObjects.size(); i++) {
            GameObject gameObject = gameObjects.get(i);
            if (gameObject instanceof Animal) {
                ((Animal) gameObject).eat(island);
            }
        }
    }

    public void timeToReproduce() {
        Island island = this;
        for (Location[] row : locations) {
            for (Location location : row) {
                List<Animal> animals = location.getAnimals();
                if (animals.size() > 1) {
                    for (Animal animal : animals) {
                        if (animal.canReproduce()) {
                            island.reproduceAnimal(animal);
                        }
                    }
                }
            }
        }
    }

    public void reproduceAnimal(Animal animal) {

        for (int i = 0; i < locations.length; i++) {
            for (int j = 0; j < locations[0].length; j++) {
                ArrayList<GameObject> animalsInLocation = locations[i][j].getGameObjects();
                ArrayList<GameObject> herbivorousList = new ArrayList<>();
                ArrayList<GameObject> predatorList = new ArrayList<>();

                for (GameObject gameObject : animalsInLocation) {
                    if (gameObject instanceof Herbivorous) {
                        herbivorousList.add(gameObject);
                    } else if (gameObject instanceof Predator) {
                        predatorList.add(gameObject);
                    }
                }

                for (GameObject gameObject : herbivorousList) {
                    if (gameObject instanceof Animal && ((Animal) gameObject).canReproduce() &&
                            herbivorousList.indexOf(gameObject) != herbivorousList.lastIndexOf(gameObject) &&
                            herbivorousList.size() < animal.getMaxQuantityAtGrid()) {
                        Animal newAnimal = ((Animal) gameObject);
                        addGameObject(newAnimal, animal.getX(), animal.getY());
//                        System.out.println("NEWBORN! " + newAnimal);
                        birthsCount++;
                        animal.setCanReproduce(false);
                    }
                }

                for (GameObject gameObject : predatorList) {
                    if (gameObject instanceof Animal && ((Animal) gameObject).canReproduce() &&
                            predatorList.indexOf(gameObject) != predatorList.lastIndexOf(gameObject) &&
                            predatorList.size() < animal.getMaxQuantityAtGrid()) {
                        Animal newAnimal = ((Animal) gameObject);

                        addGameObject(newAnimal, animal.getX(), animal.getY());
//                        System.out.println("NEWBORN! " + newAnimal);
                        birthsCount++;
                        animal.setCanReproduce(false);
                    }
                }
            }
        }
    }

    public void checkIfAnimalStarved(Animal animal) {
        Island island = this;
        if (animal.getSatiety() <= 0) {
            animal.die();
            if (animal.getLocation() != null) {
                animal.getLocation().removeGameObject(animal);
            }
            island.getDeadAnimals().add(animal);
            island.getAnimals().remove(animal);
            island.removeGameObjectFromIsland(animal);
//            System.out.printf("%s has starving to die.\n", animal.getName());
            deathsCount++;
            starvingDeaths++;
        }
    }

    public void incrementEatenAnimals() {
        eatenAnimals++;
    }
    public void incrementDeathCount() {
        deathsCount++;
    }


    public void endOfDay() {
        System.out.println("\nDays gone: " + daysGone);
        System.out.println("Animals count: " + animalsCount);
        System.out.println("Births count: " + birthsCount);
        System.out.println("Animals eaten: " + eatenAnimals);
        System.out.println("Animals starving to die: " + starvingDeaths);
        System.out.println("Deaths count: " + deathsCount);

    }
}