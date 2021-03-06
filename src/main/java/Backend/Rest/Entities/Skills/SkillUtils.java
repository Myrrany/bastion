package Backend.Rest.Entities.Skills;

import Backend.Rest.Entities.Archetype;
import Backend.Rest.Entities.Character;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class SkillUtils {

    private List<Skill> skillList = new ArrayList<>();
    private Map<Skill, Skill> prerequisites = new HashMap<>();
    private Map<Skill, List<Archetype>> archDiscounts = new HashMap<>();

    public void addSkill(Skill skill) {
        this.skillList.add(skill);
        addDiscountTypes(skill);
        addPrerequisite(skill);
    }

    private Skill checkPrerequisites(Skill s) {
        return prerequisites.get(s);
    }

    public boolean checkDiscount(Skill s, Character c) {
        return archDiscounts.get(s).contains(c.getArchetype());
    }

    public void addPrerequisite(Skill s) {
        prerequisites.put(s, s.getPrerequisite());
    }

    public void addDiscountTypes(Skill s) {
        archDiscounts.put(s, s.getDiscountArchetypes());
    }

    public boolean learnSkill(Character c, Skill s) {
        boolean prereqFound = false;
        Skill prereq = checkPrerequisites(s);
        for (Skillset skills : c.getSkillset()) {
            if (skills.getSkill() == s) {
                return skills.getSkill().isMultiAllowed();
            }
            if(prereq != null && skills.getSkill() == prereq) {
                prereqFound = true;
            }
        }
        if (prereqFound || prereq == null) {
            int cost = s.getCost();
            if (checkDiscount(s, c)) {
                cost = s.getDiscountCost();
            }
            if (c.getXp() >= cost) {
                c.addSkillToSet(new Skillset(c, s, 1), cost);
                return true;
            }
        }
        return false;
    }


}
