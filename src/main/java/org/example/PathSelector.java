package org.example;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class PathSelector {
    private static final Random random = new Random();

    public static Path choosePath(Location location, Scanner scanner) {
        List<Path> paths = location.getPaths();

        if (paths == null || paths.isEmpty()) {
            System.out.println("No paths available in this location.");
            return null;
        }

        System.out.println("Choose a path:");
        for (int i = 0; i < paths.size(); i++) {
            System.out.println((i + 1) + ". " + paths.get(i).getName());
        }

        int choice = scanner.nextInt() - 1;
        scanner.nextLine();

        if (choice < 0 || choice >= paths.size()) {
            System.out.println("Invalid choice. Choosing default path.");
            return paths.get(0);
        }

        Path chosenPath = paths.get(choice);
        System.out.println("You step onto " + chosenPath.getName() + "...");
        return chosenPath;
    }

    public static void triggerPathEvent(Path path, Creature player) {
        if (path == null || path.getPossibleEvents().isEmpty()) {
            System.out.println("Nothing special happens on this path.");
            return;
        }

        GameEvent event = path.getPossibleEvents().get(random.nextInt(path.getPossibleEvents().size()));
        event.trigger(player);
    }

    public static Enemy spawnEnemy(Path path) {
        if (path == null || path.getPossibleEnemies().isEmpty()) {
            System.out.println("The path seems peaceful... for now.");
            return null;
        }

        try {
            Class<? extends Enemy> enemyClass = path.getPossibleEnemies().get(random.nextInt(path.getPossibleEnemies().size()));
            return enemyClass.getConstructor(String.class).newInstance(enemyClass.getSimpleName() + " " + (random.nextInt(100) + 1));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
