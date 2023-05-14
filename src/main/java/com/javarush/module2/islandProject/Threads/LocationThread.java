package com.javarush.module2.islandProject.Threads;

import com.javarush.module2.islandProject.entity.Animal;
import com.javarush.module2.islandProject.entity.Island;
import com.javarush.module2.islandProject.entity.Location;
import lombok.Getter;

import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

@Getter
public class LocationThread extends Thread {

    private Island island;
    private int x;
    private int y;

    public LocationThread(int x, int y, Island island, CyclicBarrier barrier) {
        this.x = x;
        this.y = y;
        this.island = island;
    }

    @Override
    public void run() {
        Location location = island.getLocations()[x][y];
        List<Animal> animals = location.getAnimalsInLocation(location, island);
        CyclicBarrier barrier = new CyclicBarrier(100);
        try {
            for (Animal animal : animals) {
                island.moveAnimal(animal);
                animal.eat(island);
                island.reproduceAnimal(animal);
            }
            barrier.await();
        } catch (InterruptedException e) {
            interrupt();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
