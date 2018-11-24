package Backend.Rest.Entities.Crafting;

import Backend.Rest.Entities.Archetype;
import Backend.Rest.Naming;

import javax.persistence.Table;

@Table(name = Naming.CRAFTS)
public enum Craft {
    PHYSICAL(Archetype.CRAFTER),

    MENTAL(Archetype.PHYSICIAN, Archetype.ALCHEMIST),

    SMITHING(Archetype.CRAFTER),

    WOODWORKING(Archetype.CRAFTER),

    LEATHERWORKING(Archetype.CRAFTER),

    GUNSMITH(Archetype.CRAFTER),

    CONSTRUCTION(Archetype.CRAFTER),

    SPELLWEAVING(),

    ALCHEMY(Archetype.ALCHEMIST),

    MEDICAL(Archetype.PHYSICIAN);

    private Archetype[] archetypes;

    Craft(Archetype... types) {
        this.archetypes = types;
    }

    public Archetype[] getArchetypes() {
        return this.archetypes;
    }

}
