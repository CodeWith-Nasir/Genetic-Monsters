package com.example.geneticmonsters;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MonsterManager {
    private List<Monster> monsters = new ArrayList<>();

    public void createMonster(String name, String color, int strength, int speed) {
        Monster newMonster = new Monster(name, color, strength, speed);
        monsters.add(newMonster);
    }

    public List<Monster> getMonsters() {
        return new ArrayList<>(monsters); // Return a copy to avoid external modification
    }

    public void breedMonsters(Monster parent1, Monster parent2) {
        String name = parent1.getName() + "-" + parent2.getName();
        String color = (Math.random() > 0.5) ? parent1.getColor() : parent2.getColor();
        int strength = (parent1.getStrength() + parent2.getStrength()) / 2;
        int speed = (parent1.getSpeed() + parent2.getSpeed()) / 2;

        createMonster(name, color, strength, speed);
    }

    public void saveGame(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(monsters);
            System.out.println("Game saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving game: " + e.getMessage());
        }
    }

    public void loadGame(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            monsters = (List<Monster>) ois.readObject();
            System.out.println("Game loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading game: " + e.getMessage());
        }
    }
}