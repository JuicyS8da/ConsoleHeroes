package org.example;

public class TreasureEvent extends GameEvent {
    @Override
    public void trigger(Creature player) {
        System.out.println("You found a hidden treasure! +20 Gold");
    }
}