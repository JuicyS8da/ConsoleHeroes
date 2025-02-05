import java.util.Random;

class EventManager {
    private static final Random random = new Random();
    private static final GameEvent[] events = {new TreasureEvent(), new AmbushEvent(), new RestEvent()};

    public static void triggerRandomEvent(Creature player) {
        int eventIndex = random.nextInt(events.length);
        events[eventIndex].trigger(player);
    }
}