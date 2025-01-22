public class Goblin extends Enemy {

    public Goblin(String name) {
        super(name, 50, 10, 5);
    }

    @Override
    public void attack(Creature target) {
        System.out.println(getName() + " attacks " + target.getName() + " with a club!");
        target.takeDamage(getAttack());
    }
}
