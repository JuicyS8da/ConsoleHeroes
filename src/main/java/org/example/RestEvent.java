package org.example;

public class RestEvent extends GameEvent {
    @Override
    public void trigger(Creature player) {
        System.out.println("You found a safe spot to rest. +15 Health");
        player.setHealth(player.getHealth() + 15);
    }
}