public class FireballSkill extends Skill {

    public FireballSkill() {
        super("Fireball", "Launches a fireball at a single target, dealing explosive damage.", 20, 0);
    }

    @Override
    public void applyEffect(Creature user, Creature target) {
        System.out.println(user.getName() + " casts Fireball on " + target.getName() + "!");
        target.takeDamage(40);
    }
}
