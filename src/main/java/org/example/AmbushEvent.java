package org.example;

public class AmbushEvent extends GameEvent {
    @Override
    public void trigger(Creature player) {
        System.out.println("You were ambushed! You take 10 damage.");
        player.takeDamage(10);
    }
}