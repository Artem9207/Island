package com.javarush.module2.islandProject.entity;

import lombok.ToString;

@ToString

public class Grass extends GameObject {

    public Grass() {
        super("Grass", 1, 200);

    }

    @Override
    public void setMaxQuantityAtGrid(int maxQuantityAtGrid) {
        maxQuantityAtGrid = 200;
    }
}
