package com.javarush.module2.islandProject.serialization;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.javarush.module2.islandProject.animals.Herbivorous.*;
import com.javarush.module2.islandProject.animals.Predators.*;
import com.javarush.module2.islandProject.entity.Animal;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class AnimalDeserializer extends StdDeserializer<Animal> {

    public AnimalDeserializer() {
        this(null);
    }

    public AnimalDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Animal deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JacksonException {
        JsonNode node = jp.getCodec().readTree(jp);

        String animalType = node.get("Animal").asText();
        Animal animal = null;

        switch (animalType) {
            case "boar":
                animal = new Boar();
                break;
            case "buffalo":
                animal = new Buffalo();
                break;
            case "deer":
                animal = new Deer();
                break;
            case "duck":
                animal = new Duck();
                break;
            case "goat":
                animal = new Goat();
                break;
            case "horse":
                animal = new Horse();
                break;
            case "mouse":
                animal = new Mouse();
                break;
            case "rabbit":
                animal = new Rabbit();
                break;
            case "sheep":
                animal = new Sheep();
                break;
            case "caterpillar":
                animal = new Caterpillar();
                break;
            case "anaconda":
                animal = new Anaconda();
                break;
            case "bear":
                animal = new Bear();
                break;
            case "eagle":
                animal = new Eagle();
                break;
            case "fox":
                animal = new Fox();
                break;
            default:
                throw new RuntimeException("Unknown animal type: " + animalType);
        }

        animal.setName(node.get("name").asText());
        animal.setWeight(node.get("weight").asDouble());
        animal.setMaxQuantityAtGrid(node.get("maxQuantity").asInt());
        animal.setSpeed(node.get("speed").asInt());
        animal.setSatiety(node.get("satiety").asInt());
        animal.setFoodForFullSatiety(node.get("foodForFullSatiety").asInt());

        JsonNode eatProbabilityNode = node.get("eatProbability");
        Map<String, Integer> eatProbabilities = new HashMap<>();
        Iterator<String> fieldNames = eatProbabilityNode.fieldNames();
        while (fieldNames.hasNext()) {
            String fieldName = fieldNames.next();
            eatProbabilities.put(fieldName, eatProbabilityNode.get(fieldName).asInt());
        }
        animal.setEatProbability(eatProbabilities);

        return animal;
    }
}
