import java.util.List;
import java.util.ArrayList;
import java.util.Random;

abstract public class Location {
    private String name;
    private int difficulty;
    private List<Enemy> list_of_enemies;
    private List<GameEvent> possibleEvents;
    private List<Path> paths;
    private static final Random random = new Random();

    public Location(String name, int difficulty, List<Enemy> list_of_enemies, List<GameEvent> possibleEvents, List<Path> paths) {
        this.name = name;
        this.difficulty = difficulty;
        this.list_of_enemies = (list_of_enemies != null) ? list_of_enemies : new ArrayList<>();
        this.possibleEvents = (possibleEvents != null) ? possibleEvents : new ArrayList<>();
        this.paths = (paths != null) ? paths : new ArrayList<>();
    }

    public List<Path> getPaths() {
        return paths;
    }

    public String getName() {
        return this.name;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void addEnemy(Enemy enemy) {
        list_of_enemies.add(enemy);
    }

    public List<Enemy> getList_of_enemies() {
        return list_of_enemies;
    }

    public void triggerRandomEvent(Creature player) {
        if (possibleEvents.isEmpty()) {
            System.out.println("Nothing special happens in this place.");
            return;
        }

        int eventIndex = random.nextInt(possibleEvents.size());
        possibleEvents.get(eventIndex).trigger(player);
    }
}
