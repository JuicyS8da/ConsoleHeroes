package org.example;

class PiercingArrowSkill extends Skill {

    public PiercingArrowSkill() {
        super("Piercing Arrow", "An arrow that ignores part of the target's defense, dealing extra damage.", 10, 5);
    }

    @Override
    public void applyEffect(Creature user, Creature target) {
        int reducedDefense = target.getDefence() / 2;
        int damage = Math.max(0, user.getAttack() - reducedDefense + 10);
        System.out.println(user.getName() + " fires a piercing arrow at " + target.getName() + "!");
        target.takeDamage(damage);
    }
}