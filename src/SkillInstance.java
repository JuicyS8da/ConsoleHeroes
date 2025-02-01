public class SkillInstance {
    private Skill skill;
    private Creature owner;

    public SkillInstance(Skill skill, Creature owner) {
        this.skill = skill;
        this.owner = owner;
    }

    public void use(Creature target) {
        System.out.println(owner.getName() + " uses " + skill.getName() + " on " + target.getName() + "!");
        skill.applyEffect(owner, target);
    }

    public Skill getSkill() {
        return skill;
    }

    public String getName() {
        return skill.getName();
    }
}
