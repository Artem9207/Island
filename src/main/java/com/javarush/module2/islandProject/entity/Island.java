package com.javarush.module2.islandProject.entity;

import com.javarush.module2.islandProject.interfaces.Movable;
import com.javarush.module2.islandProject.service.Counter;
import lombok.Data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
public class Island {
    private Location[][] locations;
    private ArrayList<GameObject> gameObjects;
    Counter counter = new Counter();
//    private Random random;

    public Island() {
        int x = 10;
        int y = 10;
        locations = new Location[x][y];
        gameObjects = new ArrayList<>();
//        random = new Random();

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                locations[i][j] = new Location(i, j);
            }
        }
    }


    public void addGameObject(GameObject gameObject, int x, int y) {

        if (x < 0 || x >= locations.length || y < 0 || y >= locations[0].length) {
            throw new IllegalArgumentException("Некорректные координаты локации");
        }
        Location location = locations[x][y];
        location.addGameObject(gameObject);
        gameObject.setX(location.getX());
        gameObject.setY(location.getY());
//        gameObject.setLocation(location);
        gameObjects.add(gameObject);
    }

    public void removeGameObjectFromIsland(GameObject gameObject) {
        Island island = this;
        Location[][] locations = island.getLocations();
        if (locations != null) {
            for (Location[] row : locations) {
                for (Location location : row) {
                    if (location.getGameObjects().contains(gameObject)) {
                        location.removeGameObject(gameObject);
//                        gameObject.setLocation(null);
                        break;
                    }
                }
            }
        }
        island.getGameObjects().remove(gameObject);
    }

    public void moveAllAnimals() {
        Island island = this;
        List<GameObject> animals = getGameObjects();
        Iterator<GameObject> iterator = animals.iterator();
        while (iterator.hasNext()) {
            GameObject animal = iterator.next();
            if (!(animal instanceof Grass)) {
                island.moveAnimal((Movable) animal);
            }
        }
    }



    public void moveAnimal(Movable movable) {

        int currentX = movable.getX();
        int currentY = movable.getY();

        int newX = currentX + (int) (Math.random() * 3) - 1;
        int newY = currentY + (int) (Math.random() * 3) - 1;

        if (newX < 0 || newX >= locations.length || newY < 0 || newY >= locations[0].length) {
            System.out.println(movable + " stays in place.");
        } else {
            movable.move(newX, newY);
            System.out.println(movable + " moves to " + newX + ", " + newY);

            locations[currentX][currentY].removeGameObject((GameObject) movable);
            locations[newX][newY].addGameObject((GameObject) movable);
//            ((GameObject) movable).setLocation(locations[newX][newY]);
            movable.move(newX, newY);



            System.out.println(((GameObject) movable).getName() + "Animal move from (" + currentX + "," + currentY + ") to (" + newX + "," + newY + ")");
        }
    }

    public void timeToEat(Island island) {
        for (Location[] row : locations) {
            for (Location location : row) {
                ArrayList<GameObject> gameObjects = location.getGameObjects();
                List<Animal> animals = location.getAnimals(gameObjects);
                List<Animal> eatenAnimals = new ArrayList<>(); // создаем новый список для хранения съеденных животных
                if (animals.size() > 1) {
                    for (Animal animal : animals) {
                        animal.eat(island);
                        counter.incrementAnimalsEaten();
                        eatenAnimals.add(animal); // добавляем съеденное животное в список
                    }
                }
                // удаляем только съеденные животные
                for (Animal eatenAnimal : eatenAnimals) {
                    island.removeGameObjectFromIsland(eatenAnimal);
                    island.gameObjects.remove(eatenAnimal);
                    location.removeGameObject(eatenAnimal);
                    animals.remove(eatenAnimal);
                    locations[eatenAnimal.getX()][eatenAnimal.getY()].removeGameObject(eatenAnimal);
                }
            }
        }

        for (Location[] row : locations) {
            for (Location location : row) {
                ArrayList<GameObject> gameObjects = location.getGameObjects();
                List<Animal> animals = location.getAnimals(gameObjects);
                Iterator<Animal> iterator = animals.iterator();
                while (iterator.hasNext()) {
                    Animal deadAnimal = iterator.next();
                    if (!deadAnimal.isAlive()) {
                        island.removeGameObjectFromIsland(deadAnimal);
                        island.gameObjects.remove(deadAnimal);
                        location.removeGameObject(deadAnimal);
                        iterator.remove();
                        locations[deadAnimal.getX()][deadAnimal.getY()].removeGameObject(deadAnimal);
                    }
                }
            }
        }
    }



//    public void timeToEat(Island island) {
//        for (Location[] row : locations) {
//            for (Location location : row) {
//                ArrayList<GameObject> gameObjects = location.getGameObjects();
//                List<Animal> animals = location.getAnimals(gameObjects);
//                if (animals.size() > 1) {
//                    for (Animal animal : animals) {
//                        animal.eat(island);
//                        counter.incrementAnimalsEaten();
//                        if (gameObjects.stream().anyMatch(obj -> obj instanceof Animal && !((Animal) obj).isAlive())) {
//                            island.removeGameObjectFromIsland(animal);
//                            island.gameObjects.remove(animal);
//                            location.removeGameObject(animal);
//                            animals.remove(animal);
//                            locations[animal.getX()][animal.getY()].removeGameObject((animal));
//                        }
//                    }
//                }
//            }
//        }
//    }

//    public void check() {
//        Island island = this;
//
//        for (Location[] row : locations) {
//            for (Location location : row) {
//                ArrayList<GameObject> gameObjects = location.getGameObjects();
//                List<Animal> animals = location.getAnimals(gameObjects);
//
//                for (Animal animal : animals) {
//                    if (gameObjects.stream().anyMatch(obj -> obj instanceof Animal && !((Animal) obj).isAlive())) {
//                        island.removeGameObjectFromIsland(animal);
//                        island.gameObjects.remove(animal);
//                        location.removeGameObject(animal);
//                        animals.remove(animal);
//                        locations[animal.getX()][animal.getY()].removeGameObject((animal));
//                    }
//                }
//            }
//        }
//    }

    public void timeToReproduce() {
        Island island = this;
        for (Location[] row : locations) {
            for (Location location : row) {
                ArrayList<GameObject> gameObjects = location.getGameObjects();
                List<Animal> animals = location.getAnimals(gameObjects);
                if (animals.size() > 1) {
                    for (Animal animal : animals) {
                        island.reproduceAnimal(animal);
                        counter.incrementAnimalsBorn();
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

                        System.out.println("NEWBORN! " + newAnimal);
                        counter.incrementAnimalsBorn();
                        animal.setCanReproduce(false);
                    }
                }

                for (GameObject gameObject : predatorList) {
                    if (gameObject instanceof Animal && ((Animal) gameObject).canReproduce() &&
                            predatorList.indexOf(gameObject) != predatorList.lastIndexOf(gameObject) &&
                            predatorList.size() < animal.getMaxQuantityAtGrid()) {
                        Animal newAnimal = ((Animal) gameObject);

                        addGameObject(newAnimal, animal.getX(), animal.getY());
                        System.out.println("NEWBORN! " + newAnimal);
                        counter.incrementAnimalsBorn();
                        animal.setCanReproduce(false);
                    }
                }
            }
        }
    }

    public void endOfDay() {

        System.out.println("End of day:");
        System.out.printf("  Animals: %d\n", gameObjects.size());
        System.out.printf("  Animals eaten: %d\n", counter.getAnimalsEaten());
        System.out.printf("  Animals born: %d\n", counter.getAnimalsBorn());
        System.out.printf("  Animals died %d\n", counter.getAnimalsDied());
    }
}
