public class MountainTroll extends Enemy {

    public MountainTroll(String name) {
        super(name, 120, 35, 20, 0, 40);
    }

    @Override
    public void attack(Creature target) {
        System.out.println(getName() + " smashes " + target.getName() + " with a massive club!");
        target.takeDamage(getAttack());
    }
}
