package Backend.Rest.Entities;

import lombok.Getter;

@Getter
public enum Archetype {

    /**
     * This enum contains all Archetypes.
     *
     * @Author Myrthe Hultermans
     * @Version 0.1
     */

    MAGE(""), CRAFTER(""), FIGHTER(""), HUNTER(""), PHYSICIAN("");
    private String description;

    Archetype(String desc) {
        this.description = desc;
    }

    public void setDescription(String desc) {
        this.description = desc;
    }
}
