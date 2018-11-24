package Backend.Rest.Entities;

import Backend.Rest.Entities.Magic.Element;
import Backend.Rest.Entities.Magic.Spell;

import java.util.HashMap;
import java.util.Map;

public class CharacterUtils {

    public Map<Element, Integer> getElementsKnown(Character c) {
        Map<Element, Integer> elementsKnown = new HashMap<>();
        Element temp;
        for (Spell s : c.getSpellbook()) {
            temp = s.getElement();

            if (elementsKnown.containsKey(temp)) {
                elementsKnown.put(temp, elementsKnown.get(temp) + 1);
            } else {
                elementsKnown.put(temp, 1);
            }
        }
        return elementsKnown;

    }
}