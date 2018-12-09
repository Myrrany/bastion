package Backend.Rest.Entities.Magic;

import Backend.Rest.Entities.Race;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Tertiary")
@Data
@RestResource
public class TertiaryElement extends Element {

    /**
     * This class contains the template for Tertiary Elements, as well as some logic for prerequisites.
     *
     * @Author Myrthe Hultermans
     * @Version 0.1
     */

    private int prerequisiteElementsNumber;
    private int prerequisiteSpells;
    private Race prerequisiteRace;

    protected TertiaryElement() {
    }

    public TertiaryElement(String name, String description, int maxLevel, int elemNeeded, int prerequisite, Race race) {
        this.name = name;
        this.description = description;
        this.maxLevel = maxLevel;
        this.prerequisiteElementsNumber = elemNeeded;
        this.prerequisiteSpells = prerequisite;
        this.prerequisiteRace = race;
        MagicUtils.addTertiary(this);
    }

}
