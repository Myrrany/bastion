package Backend.Rest.Entities.Magic;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Primary")
public class PrimaryElement extends Element {

    /**
     * This class contains the template for Primary Elements.
     *
     * @Author Myrthe Hultermans
     * @Version 0.1
     */


    protected PrimaryElement() {
    }

    public PrimaryElement(String name, String description, int maxLevel) {
        this.name = name;
        this.description = description;
        this.maxLevel = maxLevel;
        MagicUtils.addPrimary(this);
    }


}
