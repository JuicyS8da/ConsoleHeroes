package org.example;

public abstract class Hero extends Creature {
    private final int max_health;
    private String hero_class;

    public Hero(String name, int health, int attack, int defence, int mana, int stamina, String hero_class) {
        super(name, health, attack, defence, mana, stamina);
        this.max_health = health;
        this.hero_class = hero_class;
    }

    public void heal(int amount) {
        System.out.println(getName() + " heals for " + amount + " health.");
        if (getHealth() + amount < this.max_health) {
            setHealth(getHealth() + amount);
        }
        else {
            setHealth(this.max_health);
        }

    }

    public String getHero_class() {
        return hero_class;
    }


    public int getMax_health() {
        return this.max_health;
    }
}

