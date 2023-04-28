package com.javarush.module2.islandProject;

import com.javarush.module2.islandProject.entity.Island;
import com.javarush.module2.islandProject.islandSimulation.IslandBuilder;

public class Main {
    public static void main(String[] args) {
        Island island = new Island();
        IslandBuilder builder = new IslandBuilder();
        builder.islandBuild(island);
        builder.start(island,100);
    }
}