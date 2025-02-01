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
        System.out.println("A wild " + enemy.getName() + " appeared!");
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();

        while (player.isAlive() && enemy.isAlive()) {
            System.out.println("\nChoose an action:");
            System.out.println("1. Attack");
            System.out.println("2. Use Skill");

            int action = scanner.nextInt();
            scanner.nextLine();

            switch (action) {
                case 1 -> {
                    player.attack(enemy);
                    System.out.println("\nPress Enter to continue...");
                    scanner.nextLine();
                }
                case 2 -> {
                    System.out.println("Choose a skill:");
                    for (int i = 0; i < player.getSkills().size(); i++) {
                        System.out.println((i + 1) + ". " + player.getSkills().get(i).getName());
                    }
                    int skillChoice = scanner.nextInt() - 1;
                    scanner.nextLine();
                    player.useSkill(skillChoice, enemy);
                    System.out.println("\nPress Enter to continue...");
                    scanner.nextLine();
                }
                default -> {
                    System.out.println("Invalid choice.");
                    continue; // Повторяем ввод
                }
            }

            if (enemy.isAlive()) {
                System.out.println("\nEnemy's turn...");
                enemy.attack(player);
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }

        scanner.close();

    }
}
