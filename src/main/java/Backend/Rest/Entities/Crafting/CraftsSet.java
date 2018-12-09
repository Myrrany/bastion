package Backend.Rest.Entities.Crafting;

import Backend.Rest.Naming;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import Backend.Rest.Entities.Character;
import org.springframework.data.rest.core.annotation.RestResource;

@Data
@Entity
@RestResource
@Table(name = Naming.CRAFTSET)
public class CraftsSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Naming.SET_ID)
    private int id;
    @JsonBackReference
    @ManyToOne(targetEntity = Character.class)
    @JoinColumn(name = Naming.CHAR)
    private Character character;
    @Enumerated(EnumType.STRING)
    private Craft craft;
    @Enumerated(EnumType.STRING)
    private Level level;

    protected CraftsSet() {
    }

    public CraftsSet(Character character, Craft craft, Level level) {
        this.character = character;
        this.craft = craft;
        this.level = level;
    }
}
