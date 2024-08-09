package com.example.geneticmonsters;

import java.util.List;
import java.util.Scanner;

public class GeneticMonstersgame {
    private static MonsterManager manager = new MonsterManager();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Create Monster");
            System.out.println("2. View Monsters");
            System.out.println("3. Breed Monsters");
            System.out.println("4. Save Game");
            System.out.println("5. Load Game");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = getValidInteger(scanner);

            switch (choice) {
                case 1:
                    createMonster(scanner);
                    break;
                case 2:
                    viewMonsters();
                    break;
                case 3:
                    breedMonsters(scanner);
                    break;
                case 4:
                    saveGame(scanner);
                    break;
                case 5:
                    loadGame(scanner);
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private static void createMonster(Scanner scanner) {
        System.out.print("Enter monster name: ");
        String name = scanner.nextLine();
        System.out.print("Enter monster color: ");
        String color = scanner.nextLine();
        System.out.print("Enter monster strength: ");
        int strength = getValidInteger(scanner);
        System.out.print("Enter monster speed: ");
        int speed = getValidInteger(scanner);

        if (strength < 0 || speed < 0) {
            System.out.println("Strength and speed must be non-negative integers.");
            return;
        }

        manager.createMonster(name, color, strength, speed);
        System.out.println("Monster created successfully.");
    }

    private static void viewMonsters() {
        List<Monster> monsters = manager.getMonsters();
        if (monsters.isEmpty()) {
            System.out.println("No monsters to display.");
        } else {
            System.out.println("Current Monsters:");
            for (Monster monster : monsters) {
                System.out.println(monster);
            }
        }
    }

    private static void breedMonsters(Scanner scanner) {
        List<Monster> monsters = manager.getMonsters();
        if (monsters.size() < 2) {
            System.out.println("Not enough monsters to breed.");
            return;
        }

        System.out.println("Select the first monster:");
        for (int i = 0; i < monsters.size(); i++) {
            System.out.println(i + ": " + monsters.get(i).getName());
        }
        System.out.print("Enter the number of the first monster: ");
        int firstIndex = getValidInteger(scanner);

        System.out.println("Select the second monster:");
        for (int i = 0; i < monsters.size(); i++) {
            if (i != firstIndex) {
                System.out.println(i + ": " + monsters.get(i).getName());
            }
        }
        System.out.print("Enter the number of the second monster: ");
        int secondIndex = getValidInteger(scanner);

        if (firstIndex < 0 || secondIndex < 0 || firstIndex >= monsters.size() || secondIndex >= monsters.size() || firstIndex == secondIndex) {
            System.out.println("Invalid selection.");
            return;
        }

        Monster parent1 = monsters.get(firstIndex);
        Monster parent2 = monsters.get(secondIndex);
        manager.breedMonsters(parent1, parent2);
        System.out.println("New monster created by breeding " + parent1.getName() + " and " + parent2.getName());
    }

    private static void saveGame(Scanner scanner) {
        System.out.print("Enter filename to save: ");
        String fileName = scanner.nextLine();
        manager.saveGame(fileName);
    }

    private static void loadGame(Scanner scanner) {
        System.out.print("Enter filename to load: ");
        String fileName = scanner.nextLine();
        manager.loadGame(fileName);
    }

    private static int getValidInteger(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format. Please enter a valid integer.");
            return -1;
        }
    }
}