package Backend.Rest.Entities.Crafting;

import Backend.Rest.Entities.Archetype;
import Backend.Rest.Entities.Character;

import java.util.Arrays;
import java.util.List;

public class CraftingUtils {

    public boolean levelUpCraft(Character c, Craft craft) {
        List<CraftsSet> temporary = c.getCraftsSet();
        if (craft == Craft.PHYSICAL || craft == Craft.MENTAL) {
            for (CraftsSet temp : temporary) {
                if (temp.getCraft() == craft) {
                    return false;
                }
            }
            int cost;
            if (craft.getArchetype().equals(c.getArchetype())) {
                cost = Level.BASIC.getDiscountCost();
            } else {
                cost = Level.BASIC.getCost();
            }
            if (c.getXp() >= cost) {
                c.addCraftToSet(new CraftsSet(c, craft, Level.BASIC), cost);
                return true;
            } else {
                return false;
            }
        }
        CraftsSet crafts = null;
        for (CraftsSet temp : temporary){
            if (temp.getCraft() == craft) {
                crafts = temp;
                break;
            }
        }
        Level now;
        if (crafts == null) {
            if (!checkPrerequisites(c, craft, Level.ADEPT)) {
                return false;
            } else {
                now = Level.BASIC;
            }
        } else if (crafts.getLevel() == Level.MASTER) {
            return false;
        } else {
            now = crafts.getLevel();
        }
        Level next = now.next();
        if (checkPrerequisites(c, craft, next)) {
            int cost;
            if (craft.getArchetype().equals(c.getArchetype())) {
                cost = next.getDiscountCost();
            } else {
                cost = next.getCost();
            }
            if (c.getXp() >= cost) {
                c.addCraftToSet(new CraftsSet(c, craft, next), cost);
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
        List<CraftsSet> craftsSet = c.getCraftsSet();
        if ((craft == Craft.PHYSICAL || craft == Craft.MENTAL) && l != Level.BASIC) {
            return false;
        }
        if (l == Level.BASIC && (craft == Craft.PHYSICAL || craft == Craft.MENTAL)) {
            prereqCraft = true;
        } else if (l == Level.ADEPT) {
            if (craft == Craft.SPELLWEAVING) {
                boolean physFound = false;
                boolean mentFound = false;
                for (CraftsSet set : craftsSet) {
                    if (set.getCraft() == Craft.MENTAL) {
                        mentFound = true;
                    }
                    if (set.getCraft() == Craft.PHYSICAL) {
                        physFound = true;
                    }
                    if (mentFound && physFound) {
                        break;
                    }
                }
                prereqCraft = mentFound && physFound;
            } else if (craft.getArchetype().equals(Archetype.CRAFTER)) {
                boolean physFound = false;
                for (CraftsSet set : craftsSet) {
                    if (set.getCraft() == Craft.PHYSICAL) {
                        physFound = true;
                        break;
                    }
                }
                prereqCraft = physFound;
            } else {
                boolean mentFound = false;
                for (CraftsSet set : craftsSet) {
                    if (set.getCraft() == Craft.MENTAL) {
                        mentFound = true;
                        break;
                    }
                }
                prereqCraft = mentFound;
            }
        } else {
            boolean craftOkay = false;
            for (CraftsSet set : craftsSet) {
                if (set.getCraft() == craft) {
                    craftOkay = set.getLevel() == l.previous();
                    break;
                }
            }
            prereqCraft = craftOkay;
        }
        return prereqCraft;
    }


}
