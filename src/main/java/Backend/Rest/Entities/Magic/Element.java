package Backend.Rest.Entities.Magic;

import Backend.Rest.Naming;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;

@Data
@Entity
@RestResource
@Inheritance
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Table(name = Naming.ELEMS)
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
