public class Bandit extends Enemy {

    public Bandit(String name) {
        super(name, 60, 20, 15, 10, 50);
    }

    @Override
    public void attack(Creature target) {
        System.out.println(getName() + " slashes " + target.getName() + " with a dagger!");
        target.takeDamage(getAttack());
    }
}
