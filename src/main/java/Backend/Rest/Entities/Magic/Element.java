package Backend.Rest.Entities.Magic;

import Backend.Rest.Naming;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Inheritance
@Entity
@Table (name = Naming.ELEMS)
@DiscriminatorColumn(name = Naming.ELEM_TYPE)
public abstract class Element {

    /**
     * This interface contains the template for Elements, to be supplemented with specifics per Elemental tier.
     *
     * @Author Myrthe Hultermans
     * @Version 0.1
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Naming.ELEM_ID)
    private int id;
    @Column(name = Naming.ELEM_NAME)
    String name;
    @Column(name = Naming.ELEM_DESC)
    String description;
    @Column(name = Naming.ELEM_MAX_LEVEL)
    int maxLevel;

}
