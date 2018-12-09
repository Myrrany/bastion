package Backend.Rest.Entities.Magic;

import Backend.Rest.Naming;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("Secondary")
@Data
@RestResource
public class SecondaryElement extends Element {

    /**
     * Contains the template for Secondary Elements, as well as some logic for prerequisites.
     *
     * @Author Myrthe Hultermans
     * @Version 0.1
     */
    @OneToOne
    @JoinColumn(name = Naming.ELEM_PREREQ_ONE)
    private PrimaryElement prerequisiteOne;
    @OneToOne
    @JoinColumn(name = Naming.ELEM_PREREQ_TWO)
    private PrimaryElement prerequisiteTwo;
    private int prerequisiteSpells;

    protected SecondaryElement(){}

    public SecondaryElement(String name, String description, int maxLevel, PrimaryElement one, PrimaryElement two, int prerequisite) {
        this.name = name;
        this.description = description;
        this.maxLevel = maxLevel;
        this.prerequisiteOne = one;
        this.prerequisiteTwo = two;
        this.prerequisiteSpells = prerequisite;
        MagicUtils.addSecondary(this);
    }

    public boolean setPrerequisites(PrimaryElement o, PrimaryElement t) {
        if (o.equals(t)) {
            return false;
        }
        this.prerequisiteOne = o;
        this.prerequisiteTwo = t;
        return true;
    }

    public List<PrimaryElement> getPrerequisites() {
        List<PrimaryElement> res = new ArrayList<>();
        res.add(this.prerequisiteOne);
        res.add(this.prerequisiteTwo);
        return res;
    }

    boolean setPrerequisiteOne(PrimaryElement o) {
        if (o == getPrerequisiteTwo()) {
            return false;
        }
        this.prerequisiteOne = o;
        return true;
    }


    boolean setPrerequisiteTwo(PrimaryElement t) {
        if (t == getPrerequisiteOne()) {
            return false;
        }
        this.prerequisiteTwo = t;
        return true;
    }


}
