public abstract class Creature {
    private String name;
    private int health;
    private int attack;
    private int defense;
    private float crit_chance;

    public Creature(String name, int health, int attack, int defense) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
    }

    public void takeDamage(int damage) {
        this.health -= Math.max(0, damage - defense);
        System.out.println(name + " takes " + damage + " damage. Remaining health: " + health);
    }

    public abstract void attack(Creature target);

    public boolean isAlive() {
        return health > 0;
    }

    // GETTERS AND SETTERS

    public String getName(){
        return this.name;
    }

    public int getHealth() {
        return this.health;
    }

    public int getDefense() {
        return this.defense;
    }

    public int getAttack() {
        return this.attack;
    }

    public float getCrit() {
        return this.crit_chance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setCrit(int crit) {
        this.crit_chance = crit;
    }


}
