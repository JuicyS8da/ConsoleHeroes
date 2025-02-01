import java.util.ArrayList;
import java.util.List;

public abstract class Creature {
    private String name;
    private int health;
    private int attack;
    private int defense;
    private int mana;
    private int stamina;
    private List<SkillInstance> skills = new ArrayList<>();

    public Creature(String name, int health, int attack, int defense, int mana, int stamina) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.mana = mana;
        this.stamina = stamina;
    }

    public void takeDamage(int damage) {
        this.health -= Math.max(0, damage - defense);
        System.out.println(name + " takes " + damage + " damage. Remaining health: " + health);
    }

    public void attack(Creature target) {
        System.out.println(name + " attacks " + target.getName() + "!");
        target.takeDamage(attack);
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void addSkill(Skill skill) {
        skills.add(new SkillInstance(skill, this));
    }

    public void useSkill(int skillIndex, Creature target) {
        if (skillIndex >= 0 && skillIndex < skills.size()) {
            skills.get(skillIndex).use(target);
        } else {
            System.out.println("Invalid skill choice.");
        }
    }

    public List<SkillInstance> getSkills() {
        return skills;
    }

    public String getName() {
        return this.name;
    }

    public int getAttack() {
        return this.attack;
    }

    public int getDefence() {
        return this.defense;
    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }
}
