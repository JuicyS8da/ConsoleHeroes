package org.example;

import java.util.Random;
import java.util.Scanner;

public class GameplayManager {
    private Scanner scanner;
    private Creature player;
    private Enemy enemy;
    private Random random;
    private Location location;

    public GameplayManager() {
        this.scanner = new Scanner(System.in);
        this.random = new Random();
    }

    public void saveGame() {
        try {
            System.out.println("Enter name of the save file: ");
            String saveName = scanner.nextLine();
            SaveData save = new SaveData((Hero) player, location.getLocation_id());
            DatabaseManager.saveProgress(save, saveName);
            System.out.println("Your progress has been saved by name: " + saveName);
        }
        catch (Exception e) {
            System.out.println("Something went wrong");
            System.out.println(e.getMessage());
        }
    }

    public void loadGame() {
        try {
            try {
                DatabaseManager.getAvailableSaveSlots();
                System.out.println("Enter name of the load file: ");
                String saveName = scanner.nextLine();
                SaveData load = DatabaseManager.loadSaveData(saveName);
                player = load.getPlayer();
                location = LocationManager.getLocationById(load.getLocationId());
            }
            catch (Exception e) {
                System.out.println("File not found");
            }
        }
        catch (Exception e) {
            System.out.println("Something went wrong");
            System.out.println(e.getMessage());
        }
    }

    public int mainMenu() {
        System.out.println("MAIN MENU");
        System.out.println("1. Start game");
        System.out.println("2. Load game");
        System.out.println("3. Exit game");


        int choice;

        while (true) {
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
                if (choice >= 1 && choice <= 3) break;
            }
            System.out.println("Invalid choice. Please enter 1, 2, or 3.");
            scanner.nextLine();
        }
        return choice;
    }

    public void startGame() {
        DatabaseManager.initializeDatabase();
        DatabaseManager.initializeTables();
        System.out.println("All of the sudden, you find yourself emerged in an unknown world.");
        System.out.println("You hear a voice in your head: \"Who are you, stranger?\"\n");

        int choice = mainMenu();

        switch (choice) {
            case 1 -> {
                chooseCharacter();

                while (true) {
                    chooseLocation();
                    boolean continueExploring = exploreLocation();
                    if (!continueExploring) {
                        System.out.println("Thanks for playing! See you next time.");
                        break;
                    }
                }
            }
            case 2 -> {
                loadGame();
                while (true) {
                    chooseLocation();
                    boolean continueExploring = exploreLocation();
                    if (!continueExploring) {
                        System.out.println("Thanks for playing! See you next time.");
                        break;
                    }
                }
            }
            case 3 -> {
                break;
            }
        }
    }

    private void chooseCharacter() {
        System.out.println("Choose your character: 1. Warrior 2. Mage 3. Archer");
        int choice;

        while (true) {
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
                if (choice >= 1 && choice <= 3) break;
            }
            System.out.println("Invalid choice. Please enter 1, 2, or 3.");
            scanner.nextLine();
        }

        switch (choice) {
            case 1 -> {
                player = new Warrior("Ragnar");
                System.out.println("\"Oh, the mighty Warrior? I see...\nLet your courage light the way.\"\n");
            }
            case 2 -> {
                player = new Mage("Eldrin");
                System.out.println("\"Oh, the wise Mage? I see...\nLet your wisdom lead you.\"\n");
            }
            case 3 -> {
                player = new Archer("Faelwyn");
                System.out.println("\"Oh, the quick Archer? I see...\nLet your agility be your weapon.\"\n");
            }
        }
    }

    private void chooseLocation() {
        System.out.println("\nWhere do you wish to go?");
        System.out.println("1. The dark and misty Forest 🌲");
        System.out.println("2. The cold and dangerous Mountains 🏔️");
        System.out.println("3. Exit the game ❌");

        int choice;
        while (true) {
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
                if (choice >= 1 && choice <= 4) break;
            }
            System.out.println("Invalid choice. Please enter 1, 2, or 3.");
            scanner.nextLine();
        }

        if (choice == 3) {
            System.out.println("You have chosen to leave this world. Goodbye!");
            System.exit(0);
        } else if (choice == 1) {
            location = new ForestLocation();
            System.out.println("You venture into the dense and eerie Forest...");
        } else {
            location = new MountainLocation();
            System.out.println("You climb into the unforgiving Mountains...");
        }
    }

    private boolean exploreLocation() {
        while (true) {
            System.out.println("\nWhat would you like to do?");
            System.out.println("1. Choose a path");
            System.out.println("2. Save progress");
            System.out.println("3. Exit the location");

            int choice;
            while (true) {
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                    scanner.nextLine();
                    if (choice >= 1 && choice <= 3) break;
                }
                System.out.println("Invalid choice. Please enter 1 or 2.");
                scanner.nextLine();
            }

            if (choice == 3) {
                System.out.println("You decide to leave the " + location.getName() + ". The journey continues...");
                return true;
            }
            else if (choice == 1) {
                Path chosenPath = PathSelector.choosePath(location, scanner);
                PathSelector.triggerPathEvent(chosenPath, player);

                enemy = PathSelector.spawnEnemy(chosenPath);
                if (enemy != null) {
                    System.out.println("A wild " + enemy.getName() + " appeared!");
                    fightEnemy();
                } else {
                    System.out.println("The path seems peaceful... for now.");
                }
            }
            else {
                saveGame();
            }
        }
    }

    private void fightEnemy() {
        while (player.isAlive() && (enemy != null && enemy.isAlive())) {
            playerTurn();
            if (enemy.isAlive()) {
                enemyTurn();
            }
        }

        if (player.isAlive()) {
            System.out.println("You have defeated " + enemy.getName() + "!");
        } else {
            System.out.println("You have been defeated... Game over.");
            System.exit(0);
        }
    }

    private void playerTurn() {
        System.out.println("\nChoose an action:");
        System.out.println("1. Attack");
        System.out.println("2. Use Skill");
        System.out.println("3. Run away");

        int action;
        while (true) {
            if (scanner.hasNextInt()) {
                action = scanner.nextInt();
                scanner.nextLine();
                if (action >= 1 && action <= 3) break;
            }
            System.out.println("Invalid choice. Please enter 1, 2, or 3.");
            scanner.nextLine();
        }

        switch (action) {
            case 1 -> {
                if (enemy != null) player.attack(enemy);
                else System.out.println("There are no enemies here.");
            }
            case 2 -> useSkill();
            case 3 -> {
                System.out.println("You escape from " + enemy.getName() + "!");
                return;
            }
        }
    }

    private void useSkill() {
        if (player.getSkills().isEmpty()) {
            System.out.println("No skills available.");
            return;
        }

        System.out.println("Choose a skill:");
        for (int i = 0; i < player.getSkills().size(); i++) {
            System.out.println((i + 1) + ". " + player.getSkills().get(i).getName());
        }

        int skillChoice;
        while (true) {
            if (scanner.hasNextInt()) {
                skillChoice = scanner.nextInt() - 1;
                scanner.nextLine();
                if (skillChoice >= 0 && skillChoice < player.getSkills().size()) break;
            }
            System.out.println("Invalid choice. Please enter a valid skill number.");
            scanner.nextLine();
        }

        player.useSkill(skillChoice, enemy);
    }

    private void enemyTurn() {
        System.out.println("\nEnemy's turn...");
        enemy.attack(player);
    }

    public static void main(String[] args) {
        GameplayManager game = new GameplayManager();
        game.startGame();
    }
}
