public abstract class Enemy extends Creature {

    public Enemy(String name, int health, int attack, int defence) {
        super(name, health, attack, defence);
    }

    public void roar() {
        System.out.println(getName() + " roars menacingly!");
    }
}
