import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MountainLocation extends Location {
    private static final List<Class<? extends Enemy>> possibleEnemies = new ArrayList<>();
    private static final List<GameEvent> possibleEvents = new ArrayList<>();
    private static final Random random = new Random();

    static {
        possibleEnemies.add(Bandit.class);
        possibleEnemies.add(Wolf.class);
        possibleEnemies.add(MountainTroll.class);

        possibleEvents.add(new AmbushEvent());
        possibleEvents.add(new RestEvent());
    }

    public MountainLocation() {
        super("Mountain", 2, generateEnemies(), possibleEvents, generatePaths());
    }

    private static List<Path> generatePaths() {
        List<Path> paths = new ArrayList<>();
        paths.add(new Path("Rocky Trail (Easy)", 1, List.of(Wolf.class), List.of(new RestEvent(), new TreasureEvent())));
        paths.add(new Path("Frozen Pass (Medium)", 2, List.of(Wolf.class, Bandit.class), List.of(new AmbushEvent(), new RestEvent())));
        paths.add(new Path("Troll's Lair (Hard)", 3, List.of(MountainTroll.class, Bandit.class), List.of(new AmbushEvent())));
        return paths;
    }

    private static List<Enemy> generateEnemies() {
        List<Enemy> enemies = new ArrayList<>();
        int enemyCount = random.nextInt(2) + 1;

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
            return new Bandit("Fallback Bandit");
        }
    }
}
