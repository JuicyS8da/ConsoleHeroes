import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose your character: 1. Warrior 2. Mage 3. Archer");
        int choice = scanner.nextInt();
        Creature player;

        switch (choice) {
            case 1 -> player = new Warrior("Mighty");
            // case 2 -> player = new Mage("player");
            // case 3 -> player = new Archer("player");
            default -> throw new IllegalArgumentException("Invalid choice");
        }

        Goblin enemy = new Goblin("Goblin 1");
        System.out.println("A wild " + enemy.getClass().getSimpleName() + " appeared!");

        while (player.isAlive() && enemy.isAlive()) {

            scanner.nextLine();
            player.attack(enemy);

            if (!enemy.isAlive()) {
                System.out.println(enemy.getName() + " is defeated!");
                break;
            }

            scanner.nextLine();
            enemy.attack(player);

            if (!player.isAlive()) {
                System.out.println(player.getName() + " is defeated! Game over.");
                break;
            }
        }

        scanner.close();

    }
}
