package Backend.Rest.Entities.Magic;

import Backend.Rest.Entities.Archetype;
import Backend.Rest.Entities.Character;
import Backend.Rest.Entities.CharacterUtils;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class MagicUtils {

    /**
     * This class contains utility functions for all classes that have to do with Backend.Magic.
     *
     * @Author Myrthe Hultermans
     * @Version 0.1
     */

    private static List<PrimaryElement> primaries = new ArrayList<>();
    private static List<SecondaryElement> secondaries = new ArrayList<>();
    private static List<TertiaryElement> tertiaries = new ArrayList<>();
    private CharacterUtils utils = new CharacterUtils();
    private static List<SpellLevel> levels = new ArrayList<>();

    public static void addPrimary(PrimaryElement elem) {
        MagicUtils.primaries.add(elem);
    }

    public List<PrimaryElement> getPrimaries() {
        return primaries;
    }

    public void setPrimaries(List<PrimaryElement> primaries) {
        MagicUtils.primaries = primaries;
    }

    public static void addSecondary(SecondaryElement elem) {
        MagicUtils.secondaries.add(elem);
    }

    public List<SecondaryElement> getSecondaries() {
        return secondaries;
    }

    public void setSecondaries(List<SecondaryElement> secondaries) {
        MagicUtils.secondaries = secondaries;
    }

    public static void addTertiary(TertiaryElement elem) {
        MagicUtils.tertiaries.add(elem);
    }

    public List<TertiaryElement> getTertiaries() {
        return tertiaries;
    }

    public void setTertiaries(List<TertiaryElement> tertiaries) {
        MagicUtils.tertiaries = tertiaries;
    }

    public static void addLevel(SpellLevel level) { MagicUtils.levels.add(level);

    }


    /**
     * Adds a spell to an element. This means the connection from one to the other will be made on both sides,
     * by setting the element of the spell and adding the spell to the spell-list of the element.
     *
     * @param spell   the spell to be added.
     * @param level   the level the spell will have once added to the element.
     * @param element the target element. This can be any element, including Primary, Secondary and Tertiary elements.
     */
    public void addSpell(Spell spell, SpellLevel level, Element element) {
        if (level.getLevel() < 1 || level.getLevel() > element.getMaxLevel()) {
            throw new RuntimeException("Unknown spell level");
        }
        spell.setLevel(level);
        spell.setElement(element);
    }

    /**
     * Adds a spell to the spellbook of a character, if the character has met all prerequisites and has enough
     * experience points to buy it.
     *
     * @param c the character that wants to learn a spell.
     * @param s the spell that is going to be taught.
     * @return true if the character successfully learned the spell, false if prerequisites were not met.
     */
    public boolean learnSpell(Character c, Spell s) {
        Element elem = s.getElement();
        int cost = c.getArchetype() == Archetype.MAGE ? s.getLevel().getDiscountCost() : s.getLevel().getCost();
        if (elem == null || cost > c.getXp() || c.getSpellbook().contains(s)) {
            return false;
        } else if (elem instanceof PrimaryElement && primaries.contains(elem)) {
            c.addToSpellbook(s, cost);
            return true;
        } else if (elem instanceof SecondaryElement && secondaries.contains(elem)) {
            if (SecondaryCheck(c, (SecondaryElement) elem)) {
                c.addToSpellbook(s, cost);
                return true;
            } else {
                return false;
            }
        } else if (elem instanceof TertiaryElement && tertiaries.contains(elem)) {
            if (TertiaryCheck(c, (TertiaryElement) elem)) {
                c.addToSpellbook(s, cost);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Checks whether or not a character is able to learn a spell from a specific element. This method will
     * not check if the character has the required amount of xp. Instead, it will check if the character has the
     * prerequisites.
     *
     * @param c the character that wants to learn.
     * @param elem the secondary element to be tested.
     * @return true if the character has all prerequisites to learn the element, false if they don't.
     */
    public boolean SecondaryCheck(Character c, SecondaryElement elem) {
        if (utils.getElementsKnown(c).containsKey(elem)) {
            return true;
        } else {
            Map<Element, Integer> known = utils.getElementsKnown(c);
            Element one = elem.getPrerequisiteOne();
            Element two = elem.getPrerequisiteTwo();
            int amount = elem.getPrerequisiteSpells();
            return known.containsKey(one) && known.get(one) >= amount && (known.containsKey(two) && known.get(two) >= amount);
        }
    }

    public boolean TertiaryCheck(Character c, TertiaryElement elem) {
        return utils.getElementsKnown(c).containsKey(elem) || (c.getRace() == (elem.getPrerequisiteRace()) && utils.getElementsKnown(c).size() >= elem.getPrerequisiteElementsNumber() && c.getSpellbook().size() >= elem.getPrerequisiteSpells());
    }


}
