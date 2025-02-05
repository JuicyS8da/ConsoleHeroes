import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ForestLocation extends Location {
    private static final List<Class<? extends Enemy>> possibleEnemies = new ArrayList<>();
    private static final List<GameEvent> possibleEvents = new ArrayList<>();
    private static final Random random = new Random();

    static {
        possibleEnemies.add(Goblin.class);
        possibleEnemies.add(Wolf.class);
        possibleEnemies.add(Bandit.class);

        possibleEvents.add(new AmbushEvent());
        possibleEvents.add(new TreasureEvent());
    }

    public ForestLocation() {
        super("Forest", 1, generateEnemies(), possibleEvents, generatePaths());
    }

    private static List<Path> generatePaths() {
        List<Path> paths = new ArrayList<>();
        paths.add(new Path("Beaten Path (Easy)", 1, List.of(Goblin.class), List.of(new RestEvent(), new TreasureEvent())));
        paths.add(new Path("Dark Woods (Medium)", 2, List.of(Goblin.class, Wolf.class), List.of(new AmbushEvent(), new TreasureEvent())));
        paths.add(new Path("Goblin Camp (Hard)", 3, List.of(Goblin.class, Bandit.class, Wolf.class), List.of(new AmbushEvent())));
        return paths;
    }

    private static List<Enemy> generateEnemies() {
        List<Enemy> enemies = new ArrayList<>();
        int enemyCount = random.nextInt(3) + 1;

        for (int i = 0; i < enemyCount; i++) {
            enemies.add(createRandomEnemy());
        }

        return enemies;
    }

    private static Enemy createRandomEnemy() {
        try {
            Class<? extends Enemy> enemyClass = possibleEnemies.get(random.nextInt(possibleEnemies.size()));
            return enemyClass.getConstructor(String.class).newInstance(enemyClass.getSimpleName() + " " + (random.nextInt(100) + 1));
        } catch (Exception e) {
            e.printStackTrace();
            return new Goblin("Fallback Goblin");
        }
    }
}
