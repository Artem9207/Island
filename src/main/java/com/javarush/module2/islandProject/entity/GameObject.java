package com.javarush.module2.islandProject.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public abstract class GameObject {
    private String name;
    private double weight;
    private int maxQuantityAtGrid;
//    private Location location;
    private int x;
    private int y;

    public GameObject(String name, double weight, int maxQuantityAtGrid) {
        this.name = name;
        this.weight = weight;
        this.maxQuantityAtGrid = maxQuantityAtGrid;
    }

//    public Location getLocation() {
//        return location;
//    }

//    public void setLocation(Location location) {
//        this.location = location;
//    }
}
