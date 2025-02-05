public class Archer extends Hero {

    public Archer(String name) {
        super(name, 80, 15, 5, 30, 50);
        addSkill(new PiercingArrowSkill());
    }

    @Override
    public void attack(Creature target) {
        System.out.println(getName() + " shoots an arrow at " + target.getName() + "!");
        target.takeDamage(getAttack());
    }
}