public abstract class Enemy extends Creature {

    public Enemy(String name, int health, int attack, int defence, int mana, int stamina) {
        super(name, health, attack, defence, mana, stamina);
    }

    public void roar() {
        System.out.println(getName() + " roars menacingly!");
    }
}
