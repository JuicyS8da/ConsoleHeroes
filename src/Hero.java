public abstract class Hero extends Creature {
    private final int max_health;

    public Hero(String name, int health, int attack, int defence) {
        super(name, health, attack, defence);
        this.max_health = health;
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

    public int getMax_health() {
        return this.max_health;
    }
}

