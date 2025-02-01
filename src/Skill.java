abstract class Skill {
    private String name;
    private String description;
    private int mana_cost;
    private int stamina_cost;

    public Skill(String name, String description, int mana_cost, int stamina_cost) {
        this.name = name;
        this.description = description;
        this.mana_cost = mana_cost;
        this.stamina_cost = stamina_cost;
    }

    public abstract void applyEffect(Creature user, Creature target);

    // GETTERS AND SETTERS

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMana_cost() {
        return mana_cost;
    }

    public void setMana_cost(int mana_cost) {
        this.mana_cost = mana_cost;
    }

    public int getStamina_cost() {
        return stamina_cost;
    }

    public void setStamina_cost(int stamina_cost) {
        this.stamina_cost = stamina_cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
