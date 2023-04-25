package com.javarush.module2.islandProject;

import com.javarush.module2.islandProject.animals.Herbivorous.Boar;
import com.javarush.module2.islandProject.animals.Herbivorous.Buffalo;
import com.javarush.module2.islandProject.animals.Herbivorous.Deer;
import com.javarush.module2.islandProject.animals.Predators.Bear;
import com.javarush.module2.islandProject.animals.Predators.Wolf;
import com.javarush.module2.islandProject.entity.Animal;
import com.javarush.module2.islandProject.entity.GameObject;
import com.javarush.module2.islandProject.entity.Grass;
import com.javarush.module2.islandProject.entity.Island;
import com.javarush.module2.islandProject.interfaces.Movable;
import com.javarush.module2.islandProject.islandSimulation.IslandBuilder;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");


        Island island = new Island();
        IslandBuilder builder = new IslandBuilder();
        builder.islandBuild(island);
        builder.start(island,2);

        System.out.println("end");

    }
}