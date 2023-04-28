package com.javarush.module2.islandProject.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.javarush.module2.islandProject.interfaces.Movable;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ToString
@Getter
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)

public abstract class Animal extends GameObject implements Movable {
    private int speed;
    private double satiety;
    private double foodForFullSatiety;
    private Map<String, Integer> eatProbability;
    private boolean isAlive = true;
    private boolean canReproduce =true;

    public void setSatiety(double satiety) {
        if (satiety > 100) {
            this.satiety = 100;
        } else {
            this.satiety = satiety;
        }
    }

    public Animal(String name, double weight, int maxQuantityAtGrid, int speed, double satiety, double foodForFullSatiety, Map<String, Integer> eatProbability) {
        super(name, weight, maxQuantityAtGrid);
        this.speed = speed;
        this.satiety = satiety;
        this.foodForFullSatiety = foodForFullSatiety;
        this.eatProbability = eatProbability;
    }





    public void eat(Island island) {
        Location[][] locations = island.getLocations();
        Location location = null;


        if (!isAlive()) {
            return;
        }

        if (this.satiety >= 100) {
            return;
        }

        for (Location[] row : locations) {
            for (Location loc : row) {
                location = loc;
                ArrayList<GameObject> gameObjectsAtLocation = location.getGameObjects();
                List<Animal> animalsAtLocation = location.getAnimalsInLocation(location);
                List<Animal> deadAnimals = new ArrayList<>();
                List<Grass> grassAtLocation = new ArrayList<>();

                for (GameObject gameObject : gameObjectsAtLocation) {
                    if (gameObject instanceof Grass) {
                        grassAtLocation.add((Grass) gameObject);
                    }
                }

                Map<String, Integer> eatProbability = this.getEatProbability();
                for (Animal other : animalsAtLocation) {
                    if (other instanceof Herbivorous) {
                        Integer probability = eatProbability.get(other.getClass().getSimpleName());
                        if (probability != null && probability > 0) {
                            double foodValue = other.getFoodForFullSatiety() * ((Herbivorous) other).getWeight();
                            int attempt = (int) (Math.random() * 100);
                            if (attempt <= probability) {
                                this.setSatiety(this.getSatiety() + foodValue);
//                                System.out.printf("%s has eaten %s, gaining %f points of satiety.\n", this.getName(), other.getName(), foodValue);
                                island.incrementEatenAnimals();
                                island.incrementDeathCount();
                                location.removeAnimal(other);
                                other.setAlive(false);
                                deadAnimals.add(other);

                                return;
                            } else {
//                                System.out.printf("%s attempted to eat %s, but failed.\n", this.getName(), other.getName());
                                this.decreaseSatiety(1);
                                island.checkIfAnimalStarved(this);
                            }
                        }
                    }
                }
//               this.decreaseSatiety(1);
                island.checkIfAnimalStarved(this);


                for (Animal deadAnimal : deadAnimals) {
                    location.removeGameObject(deadAnimal);
                    island.getDeadAnimals().add(deadAnimal);
                }

                for (Grass grass : grassAtLocation) {
                    Integer probability = eatProbability.get(grass.getClass().getSimpleName());
                    if (probability != null && probability > 0) {
                        double foodValue = 5.0 * grass.getWeight();
                        int attempt = (int) (Math.random() * 100);
                        if (attempt <= probability) {
                            this.setSatiety(this.getSatiety() + foodValue);
//                            System.out.printf("%s has eaten %s, gaining %f points of satiety.\n", this.getName(), grass.getName(), foodValue);
                            location.removeGameObject(grass);
                            island.removeGameObjectFromIsland(grass);
                            return;
                        }
                    }
                }
            }
        }
//        System.out.printf("%s found no food.\n", this.getName());
       this.decreaseSatiety(1);

       island.checkIfAnimalStarved(this);
    }

    public void decreaseSatiety(int amount) {
        satiety -= amount;
    }

    public boolean canReproduce() {
        return canReproduce;
    }

    public void setCanReproduce(boolean canReproduce) {
        this.canReproduce = canReproduce;
    }


    public void die() {
        isAlive = false;
    }
}