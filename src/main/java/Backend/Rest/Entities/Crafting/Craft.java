package Backend.Rest.Entities.Crafting;

import Backend.Rest.Entities.Archetype;
import Backend.Rest.Naming;

import javax.persistence.Table;

@Table(name = Naming.CRAFTS)
public enum Craft {
    PHYSICAL(Archetype.CRAFTER),

    MENTAL(Archetype.PHYSICIAN),

    SMITHING(Archetype.CRAFTER),

    WOODWORKING(Archetype.CRAFTER),

    LEATHERWORKING(Archetype.CRAFTER),

    GUNSMITH(Archetype.CRAFTER),

    CONSTRUCTION(Archetype.CRAFTER),

    SPELLWEAVING(null),

    ALCHEMY(Archetype.PHYSICIAN),

    MEDICAL(Archetype.PHYSICIAN);

    private Archetype archetype;

    Craft(Archetype type) {
        this.archetype = type;
    }

    public Archetype getArchetype() {
        return this.archetype;
    }

}
