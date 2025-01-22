public class Warrior extends Hero {

    public Warrior(String name) {
        super(name, 100, 20, 15);
    }

    @Override
    public void attack(Creature target) {
        System.out.println(getName() + " swings their sword at " + target.getName() + "!");
        target.takeDamage(getAttack());
    }
}

