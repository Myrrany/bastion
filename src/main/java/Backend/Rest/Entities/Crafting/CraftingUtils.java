package Backend.Rest.Entities.Crafting;

import Backend.Rest.Entities.Archetype;
import Backend.Rest.Entities.Character;

import java.util.Arrays;
import java.util.Map;

public class CraftingUtils {

    public boolean levelUpCraft(Character c, Craft craft) {
        Map<Craft, Level> temp = c.getCraftsSet();
        if (temp.get(craft) == Level.MASTER) {
            return false;
        } else if ((craft == Craft.PHYSICAL || craft == Craft.MENTAL) && temp.containsKey(craft)) {
            return false;
        } else if (craft == Craft.PHYSICAL || craft == Craft.MENTAL) {
            int cost;
            if (Arrays.asList(craft.getArchetypes()).contains(c.getArchetypeId())) {
                cost = Level.BASIC.getDiscountCost();
            } else {
                cost = Level.BASIC.getCost();
            }
            if (c.getXp() >= cost) {
                c.addCraftToSet(craft, Level.BASIC, cost);
                return true;
            } else {
                return false;
            }
        }
        if (!temp.containsKey(craft)) {
            if (!checkPrerequisites(c, craft, Level.ADEPT)) {
                return false;
            }
        }
        Level now = temp.get(craft);
        Level next;
        if (now != null) {
            next = temp.get(craft).next();
        } else {
            next = Level.ADEPT;
        }
        if (checkPrerequisites(c, craft, next)) {
            int cost;
            if (Arrays.asList(craft.getArchetypes()).contains(c.getArchetypeId())) {
                cost = next.getDiscountCost();
            } else {
                cost = next.getCost();
            }
            if (c.getXp() >= cost) {
                c.addCraftToSet(craft, next, cost);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean checkPrerequisites(Character c, Craft craft, Level l) {
        boolean prereqCraft;
        Map<Craft, Level> craftSkills = c.getCraftsSet();
        if ((craft == Craft.PHYSICAL || craft == Craft.MENTAL) && l != Level.BASIC) {
            return false;
        }
        if (l == Level.BASIC && (craft == Craft.PHYSICAL || craft == Craft.MENTAL)) {
            prereqCraft = true;
        } else if (l == Level.ADEPT) {
            if (craft == Craft.SPELLWEAVING) {
                prereqCraft = craftSkills.containsKey(Craft.MENTAL) && craftSkills.containsKey(Craft.PHYSICAL);
            } else if (Arrays.asList(craft.getArchetypes()).contains(Archetype.CRAFTER)) {
                prereqCraft = craftSkills.containsKey(Craft.PHYSICAL);
            } else {
                prereqCraft = craftSkills.containsKey(Craft.MENTAL);
            }
        } else {
            prereqCraft = craftSkills.get(craft) == l.previous();
        }
        return prereqCraft;
    }


}
