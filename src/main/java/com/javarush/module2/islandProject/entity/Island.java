package com.javarush.module2.islandProject.entity;

import com.javarush.module2.islandProject.Threads.LocationThread;
import com.javarush.module2.islandProject.interfaces.Movable;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;

@Data
public class Island {
    public static final int DAY_LENGTH = 1000;
    private Location[][] locations;
    private ArrayList<GameObject> gameObjects;
    private ArrayList<Animal> animals;
    private List<Animal> deadAnimals = new ArrayList<>();
    private Object mutex = new Object();
    private LocationThread[][] locationThreads;
    private List<LocationThread> threadList = new ArrayList<>();
    private int animalsCount = 0;
    private int eatenAnimals = 0;
    private int birthsCount = 0;
    private int deathsCount = 0;
    private int starvingDeaths = 0;
    private int daysGone;
    Object lock = new Object();

    public Island() {
        int x = 10;
        int y = 10;
        locations = new Location[x][y];
        gameObjects = new ArrayList<>();
        animals = new ArrayList<>();
        locationThreads = new LocationThread[x][y];
    }

    public void addGameObject(GameObject gameObject, int x, int y) {
        synchronized (locations) {
            if (x < 0 || x >= locations.length || y < 0 || y >= locations[0].length) {
                throw new IllegalArgumentException("Некорректные координаты локации");
            }
            Location location = locations[x][y];

            if (location == null) {
                location = new Location(x, y);
                locations[x][y] = location;
            }

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
    }

    public void removeGameObjectFromIsland(GameObject gameObject) {
        synchronized (mutex) {
            Island island = this;
            Location[][] locations = island.getLocations();
            if (gameObject instanceof Animal animal) {
                if (!animal.isAlive()) {
                    deadAnimals.add(animal);
                    deathsCount++;
                    animalsCount--;
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
            island.getDeadAnimals().remove(gameObject);
        }
    }

    public void moveAnimal(Movable movable) {
        synchronized (mutex) {
            int currentX = movable.getX();
            int currentY = movable.getY();
            int speed = movable.getSpeed();
            int newX = currentX;
            int newY = currentY;

            while ((newX == currentX && newY == currentY) || newX < 0 || newX >= locations.length || newY < 0 || newY >= locations[0].length) {
                newX = currentX + (int) (Math.random() * 3) - speed;
                newY = currentY + (int) (Math.random() * 3) - speed;
            }

            movable.move(newX, newY);
            locations[currentX][currentY].removeAnimal((Animal) movable);
            locations[newX][newY].addAnimal((Animal) movable);
            ((Animal) movable).setLocation(locations[newX][newY]);
            movable.move(newX, newY);

//            System.out.println(movable + "move");
        }
    }


    public void reproduceAnimal(Animal animal) {
        for (int i = 0; i < locations.length; i++) {
            for (int j = 0; j < locations[0].length; j++) {
                List<GameObject> animalsInLocation = locations[i][j].getGameObjects();
               List<GameObject> herbivorousList = new ArrayList<>();
               List<GameObject> predatorList = new ArrayList<>();

                for (GameObject gameObject : animalsInLocation) {
                    if (gameObject instanceof Herbivorous) {
                        herbivorousList.add(gameObject);
                    } else if (gameObject instanceof Predator) {
                        predatorList.add(gameObject);
                    }
                }

                synchronized (this) {
                    for (GameObject gameObject : herbivorousList) {
                        if (gameObject instanceof Animal && ((Animal) gameObject).canReproduce() &&
                                herbivorousList.indexOf(gameObject) != herbivorousList.lastIndexOf(gameObject) &&
                                herbivorousList.size() < animal.getMaxQuantityAtGrid()) {
                            Animal newAnimal = ((Animal) gameObject);
                            addGameObject(newAnimal, animal.getX(), animal.getY());
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
                            birthsCount++;
                            animal.setCanReproduce(false);
                        }
                    }
                }
            }
        }
    }

    public void checkIfAnimalStarved(Animal animal) {
        synchronized (this) {
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
    }

    public synchronized void incrementEatenAnimals() {
        eatenAnimals++;
    }
    public synchronized void incrementDeathCount() {
        deathsCount++;
    }
    public synchronized void incrementDaysGone() {
        daysGone++;
    }


    public synchronized void endOfDaySync() {
        System.out.println("\nDays gone: " + daysGone);
        System.out.println("Animals count: " + animalsCount);
        System.out.println("Births count: " + birthsCount);
        System.out.println("Animals eaten: " + eatenAnimals);
        System.out.println("Animals starving to die: " + starvingDeaths);
        System.out.println("Deaths count: " + deathsCount);
    }

    public void simulate(int daysInSimulation) {
        for (int i = 0; i < daysInSimulation; i++) {
            CyclicBarrier barrier = new CyclicBarrier(locations.length);
            List<LocationThread> threads = new ArrayList<>();

            for (int x = 0; x < locations.length; x++) {
                for (int y = 0; y < locations[x].length; y++) {
                    LocationThread thread = new LocationThread(x, y, this, barrier);
                    threads.add(thread);
                }
            }
                startAllThreads(threads);
            try {
                Thread.sleep(DAY_LENGTH);
                incrementDaysGone();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            endOfDaySync();
        }
    }

    private synchronized void startAllThreads(List<LocationThread> threads) {
        for (LocationThread thread : threads) {
            thread.start();
        }
    }
}