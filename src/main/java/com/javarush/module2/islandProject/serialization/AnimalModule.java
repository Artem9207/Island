package com.javarush.module2.islandProject.serialization;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.javarush.module2.islandProject.entity.Animal;

public class AnimalModule extends SimpleModule {

    public AnimalModule() {
        addDeserializer(Animal.class, new AnimalDeserializer());
    }
}
