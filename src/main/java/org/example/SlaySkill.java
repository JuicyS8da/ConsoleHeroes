package org.example;

public class SlaySkill extends Skill {

    public SlaySkill() {
        super("Slay", "A precise and powerful attack that exploits the enemy's weak spot, dealing massive damage.", 0, 10);
    }

    @Override
    public void applyEffect(Creature user, Creature target) {
        int damage = (int) Math.round(user.getAttack() * 1.5);
        target.takeDamage(damage);
    }
}
