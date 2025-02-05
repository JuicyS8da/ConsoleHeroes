import java.util.List;

public class Path {
    private String name;
    private int difficulty;
    private List<Class<? extends Enemy>> possibleEnemies;
    private List<GameEvent> possibleEvents;

    public Path(String name, int difficulty, List<Class<? extends Enemy>> possibleEnemies, List<GameEvent> possibleEvents) {
        this.name = name;
        this.difficulty = difficulty;
        this.possibleEnemies = possibleEnemies;
        this.possibleEvents = possibleEvents;
    }

    public String getName() {
        return name;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public List<Class<? extends Enemy>> getPossibleEnemies() {
        return possibleEnemies;
    }

    public List<GameEvent> getPossibleEvents() {
        return possibleEvents;
    }
}
