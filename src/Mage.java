public class Mage extends Hero {

    public Mage(String name) {
        super(name, 100, 5, 0, 100, 20);
        addSkill(new FireballSkill());
    }

    @Override
    public void attack(Creature target) {
        System.out.println(getName() + " swings their staff at " + target.getName() + "!");
        target.takeDamage(getAttack());
    }
}
