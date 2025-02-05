public class Wolf extends Enemy {

    public Wolf(String name) {
        super(name, 40, 30, 10, 0, 60);
    }

    @Override
    public void attack(Creature target) {
        System.out.println(getName() + " bites " + target.getName() + " fiercely!");
        target.takeDamage(getAttack());
    }
}
